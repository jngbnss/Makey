// ProfileList.jsx
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function ProfileList() {
  const [profiles, setProfiles] = useState([]);
  const [newProfile, setNewProfile] = useState({ name: "", email: "" });
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:8080/api/profiles")
      .then(res => setProfiles(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleAddProfile = () => {
    if (newProfile.name && newProfile.email) {
      axios.post("http://localhost:8080/api/profiles", newProfile)
        .then(res => {
          setProfiles([...profiles, res.data]);
          setNewProfile({ name: "", email: "" }); // 추가 후 입력 값 초기화
        })
        .catch(err => console.error(err));
    }
  };

  const handleGoBack = () => {
    navigate("/");  // 메인 페이지로 돌아감
  };

  return (
    <div className="app-container">
      <h1>프로필 목록</h1>
      <div>
        <input
          className="app-input"
          type="text"
          placeholder="이름"
          value={newProfile.name}
          onChange={(e) => setNewProfile({ ...newProfile, name: e.target.value })}
        />
        <input
          className="app-input"
          type="email"
          placeholder="이메일"
          value={newProfile.email}
          onChange={(e) => setNewProfile({ ...newProfile, email: e.target.value })}
        />
        <button className="app-button" onClick={handleAddProfile}>추가</button>
      </div>

      <button className="app-back-button" onClick={handleGoBack}>뒤로가기</button>

      <div style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gap: "20px" }}>
        {profiles.map(profile => (
          <div
            key={profile.id}
            className="app-profile-card"
            onClick={() => navigate(`/profile/${profile.id}`)}
          >
            <h3>{profile.name}</h3>
            <p>{profile.email}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
