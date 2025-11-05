import { useNavigate } from "react-router-dom";
import "./Main.css";

export default function Main() {
  const navigate = useNavigate();

  const items = [
    "프로필",
    "게시판",
    "노인",
    "쇼핑",
    "생활/가정",
    "경제/재테크",
    "건강/의료",
    "행정/법률",
    "쇼핑",
  ];

  const handleClick = (item) => {
    if (item === "게시판") {
      navigate("/posts"); // 게시판 클릭 시 게시판 목록 페이지로 이동
    }
    if (item === "노인") {
      navigate("/elderly");
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
