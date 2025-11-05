import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./ElderlyMain.css";

export default function ElderlyMain() {
  const navigate = useNavigate();
  const [persons, setPersons] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/persons")
      .then((res) => setPersons(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="elderly-container">
      <div className="header">
        <button className="back-btn" onClick={() => navigate("/")}>
          ← 뒤로가기
        </button>
        <h2 className="title">노인관리</h2>
        <button className="new-btn" onClick={() => navigate("/elderly/new")}>
          + 등록
        </button>
      </div>

      <div className="content">
        {persons.map((person) => (
          <div
            key={person.id}
            className="elder-card"
            onClick={() => navigate(`/elderly/${person.id}`)}
          >
            <p>
              <strong>이름:</strong> {person.name} <br />
              <strong>보호자:</strong> {person.guardianName} <br />
              <strong>상태:</strong> {person.status}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
