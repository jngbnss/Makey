// Profile.jsx
import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";

export default function Profile() {
  const { id } = useParams();
  const [profile, setProfile] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/profiles/${id}`)
      .then(res => setProfile(res.data))
      .catch(err => console.error(err));
  }, [id]);

  const handleGoBack = () => {
    navigate("/profile");  // 프로필 목록 페이지로 돌아가기
  };

  const handleDelete = () => {
    axios.delete(`http://localhost:8080/api/profiles/${id}`)
      .then(() => {
        navigate("/profile");  // 삭제 후 프로필 목록으로 돌아가기
      })
      .catch(err => console.error(err));
  };

  const handleUpdate = () => {
    const updatedProfile = { ...profile, name: "Updated Name" }; // 예시로 이름을 수정
    axios.put(`http://localhost:8080/api/profiles/${id}`, updatedProfile)
      .then(res => {
        setProfile(res.data);
      })
      .catch(err => console.error(err));
  };

  if (!profile) return <div>Loading...</div>;

  return (
    <div className="app-container">
      <h1>상세 프로필</h1>
      <div className="selected-post">
        <h2>{profile.name}</h2>
        <p>Email: {profile.email}</p>
        <button className="app-button" onClick={handleUpdate}>수정</button>
        <button className="app-button" onClick={handleDelete}>삭제</button>
        <button className="app-back-button" onClick={handleGoBack}>뒤로가기</button>
      </div>
    </div>
  );
}
