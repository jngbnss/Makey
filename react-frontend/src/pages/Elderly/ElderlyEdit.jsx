import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./ElderlyEdit.css";

export default function ElderlyEdit() {
  const { id } = useParams();
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

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/persons/${id}`)
      .then((res) => setFormData(res.data))
      .catch((err) => console.error(err));
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: name === "age" ? Number(value) : value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:8080/api/persons/${id}`, formData)
      .then(() => navigate(`/elderly/${id}`));
  };

  return (
    <div className="elderly-edit-container">
      <h2>노인 수정</h2>
      <form onSubmit={handleSubmit}>
        <input name="name" value={formData.name} onChange={handleChange} required />
        <input name="age" type="number" value={formData.age} onChange={handleChange} required />
        <input name="dementiaLevel" value={formData.dementiaLevel} onChange={handleChange} required />
        <input name="guardianName" value={formData.guardianName} onChange={handleChange} required />
        <input name="guardianPhone" value={formData.guardianPhone} onChange={handleChange} required />
        <input name="lastSeenPlace" value={formData.lastSeenPlace} onChange={handleChange} required />
        <select name="status" value={formData.status} onChange={handleChange}>
          <option value="MISSING">실종</option>
          <option value="FOUND">발견</option>
        </select>
        <button type="submit">수정 완료</button>
        <button type="button" onClick={() => navigate(`/elderly/${id}`)}>취소</button>
      </form>
    </div>
  );
}
