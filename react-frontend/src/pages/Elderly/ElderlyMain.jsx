import { useNavigate } from "react-router-dom";
import "./ElderlyMain.css";

export default function ElderlyMain() {
  const navigate = useNavigate();

  return (
    <div className="elderly-container">
      {/* 상단 바 */}
      <div className="header">
        <button className="back-btn" onClick={() => navigate("/")}>
          ← 뒤로가기
        </button>
        <h2 className="title">노인관리</h2>
        <button className="new-btn">+ 등록</button>
      </div>

      {/* 메인 콘텐츠 */}
      <div className="content">
        <div className="elder-card">
          <p>
            <strong>이름:</strong> 박땡땡 <br />
            <strong>보호자:</strong> 박땡땡 <br />
            <strong>상세설명:</strong> 땡땡땡
          </p>
        </div>
        <div className="elder-card">
          <p>
            <strong>이름:</strong> 김땡땡 <br />
            <strong>보호자:</strong> 김땡땡 <br />
            <strong>상세설명:</strong> 땡땡땡
          </p>
        </div>
      </div>
    </div>
  );
}
