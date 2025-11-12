import { useNavigate } from "react-router-dom";
import "./Main.css";

export default function Main() {
  const navigate = useNavigate();

  const items = [
    "프로필",
    "게시판",
    "실종된 치매 노인 위치 추적",
    "쇼핑",
    "생활/가정",
    "경제/재테크",
    "건강/의료",
    "행정/법률",
    "채팅",
    "레이싱 게임", // 새로 추가
  ];

  const handleClick = (item) => {
    switch(item) {
      case "게시판":
        navigate("/posts");
        break;
      case "실종된 치매 노인 위치 추적":
        navigate("/elderly");
        break;
      case "레이싱 게임":
        navigate("/game"); // 게임 페이지로 이동
        break;
        case "채팅":
        // ✅ Spring Boot 서버의 HTML로 이동
        //window.location.href = "http://localhost:8080/randomNicknameChat.html";
        window.location.href = "http://localhost:8080/chatv1/index.html";
        break;
      default:
        alert(`${item} 페이지는 아직 준비되지 않았습니다`);
    }
  };

  return (
    <div className="container">
      <div className="grid-box">
        {items.map((item, idx) => (
          <div key={idx} className="card" onClick={() => handleClick(item)}>
            {item}
          </div>
        ))}
      </div>
    </div>
  );
}
