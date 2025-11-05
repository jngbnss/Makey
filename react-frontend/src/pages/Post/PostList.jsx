import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";  // useNavigate 임포트
import axios from "axios";
import "./PostList.css"; // 스타일링은 별도로 추가

export default function PostList() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();  // useNavigate 훅을 사용

  // 게시글 목록 가져오기
  useEffect(() => {
    axios
      .get("http://localhost:8080/posts") // 백엔드 서버 주소
      .then((response) => {
        setPosts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching posts:", error);
      });
  }, []);

  // 게시글 클릭 시 상세 페이지로 이동
  const goToPostDetail = (id) => {
    navigate(`/posts/${id}`);  // 게시글 ID를 포함한 상세 페이지로 이동
  };

  // 뒤로 가기 버튼 클릭 시 메인 페이지로 이동
  const goBack = () => {
    navigate("/");  // 메인 페이지로 이동
  };

  // 등록하기 버튼 클릭 시 게시물 등록 페이지로 이동
  const goToCreatePost = () => {
    navigate("/posts/create");  // 게시물 등록 페이지로 이동 (URL은 예시)
  };

  return (
    <div className="post-list-container">
      <div className="button-container">
        <button className="back-button" onClick={goBack}>뒤로가기</button>
        <button className="create-button" onClick={goToCreatePost}>등록하기</button>
      </div>

      <h1>게시판</h1>
      <div className="post-list">
        {posts.map((post) => (
          <div
            key={post.id}
            className="post-card"
            onClick={() => goToPostDetail(post.id)} // 클릭 시 상세 페이지로 이동
          >
            <h3>{post.title}</h3>
            <p>{post.content}</p>
            <p><strong>작성자:</strong> {post.author}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
