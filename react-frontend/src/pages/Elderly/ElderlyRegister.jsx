import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./ElderlyRegister.css";

export default function ElderlyRegister() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: "",
    age: "",
    dementiaLevel: "",
    guardianName: "",
    guardianPhone: "",
    lastSeenPlace: "",
    status: "MISSING",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "age" ? Number(value) : value,
    });
  };

  const handleSubmit = (e) => {
    e?.preventDefault();
    axios
      .post("http://localhost:8080/api/persons", formData)
      .finally(() => {
        // 성공 여부 상관없이 목록 페이지로 이동
        navigate("/elderly");
      });
  };

  return (
    <div className="elderly-register-container">
      <div className="header">
        <button className="back-btn" onClick={() => navigate("/elderly")}>
          ← 뒤로가기
        </button>
        <h2 className="title">노인 등록</h2>
        <button className="submit-btn" onClick={handleSubmit}>
          등록
        </button>
      </div>

      <form className="register-form" onSubmit={handleSubmit}>
        <input
          name="name"
          placeholder="이름"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          name="age"
          type="number"
          placeholder="나이"
          value={formData.age}
          onChange={handleChange}
          required
        />
        <input
          name="dementiaLevel"
          placeholder="치매 수준"
          value={formData.dementiaLevel}
          onChange={handleChange}
          required
        />
        <input
          name="guardianName"
          placeholder="보호자 이름"
          value={formData.guardianName}
          onChange={handleChange}
          required
        />
        <input
          name="guardianPhone"
          placeholder="보호자 전화번호"
          value={formData.guardianPhone}
          onChange={handleChange}
          required
        />
        <input
          name="lastSeenPlace"
          placeholder="최근 발견 위치"
          value={formData.lastSeenPlace}
          onChange={handleChange}
          required
        />
        <select
          name="status"
          value={formData.status}
          onChange={handleChange}
        >
          <option value="MISSING">실종</option>
          <option value="FOUND">발견</option>
        </select>
      </form>
    </div>
  );
}
