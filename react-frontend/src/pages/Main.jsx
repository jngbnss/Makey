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
    "레이싱 게임",
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
        navigate("/game");
        break;
      case "채팅":
        window.location.href = "http://localhost:8080/chat/index.html";
        break;
      default:
        alert(`${item} 페이지는 아직 준비되지 않았습니다`);
    }
  };

  return (
    <div className="container">
      {/* 상단 로고 + 슬로건 */}
      <header className="main-header">
        <h1 className="main-title">MAKEY</h1>
        <p className="main-subtitle">Make your day, make your life with Makey</p>
      </header>

      {/* 카드 메뉴 */}
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
