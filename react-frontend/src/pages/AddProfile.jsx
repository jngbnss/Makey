// src/pages/AddProfile.jsx
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AddProfile() {
  const [profile, setProfile] = useState({ name: "", email: "" });
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProfile((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSaveProfile = () => {
    if (profile.name && profile.email) {
      axios.post("http://localhost:8080/api/profiles", profile)
        .then(() => {
          // 프로필 추가 후 목록 페이지로 돌아가기
          navigate("/profile");
        })
        .catch(err => console.error(err));
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>프로필 추가</h1>
      <div>
        <input
          type="text"
          placeholder="이름"
          name="name"
          value={profile.name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <input
          type="email"
          placeholder="이메일"
          name="email"
          value={profile.email}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <button onClick={handleSaveProfile}>저장</button>
        <button onClick={() => navigate("/profile")}>취소</button>
      </div>
    </div>
  );
}
