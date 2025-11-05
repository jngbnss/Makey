import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./ElderlyDetail.css";

export default function ElderlyDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [person, setPerson] = useState(null);
  const [location, setLocation] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/persons/${id}`)
      .then((res) => setPerson(res.data))
      .catch((err) => console.error(err));
  }, [id]);

  const handleDelete = () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios.delete(`http://localhost:8080/api/persons/${id}`).then(() => {
        navigate("/elderly");
      });
    }
  };

  const handleStartRandomGPS = () => {
    // 알림을 통해 GPS 추적 시작 안내
    alert("GPS 위치 추적이 시작되었습니다. 위치 정보를 업데이트 중입니다.");

    // 랜덤 위치 업데이트 요청
    axios
      .put(`http://localhost:8080/api/persons/${id}/updateRandomLocation`)
      .then((res) => {
        const roundedLatitude = res.data.latitude.toFixed(2);  // 위도 소수점 2자리 반올림
        const roundedLongitude = res.data.longitude.toFixed(2);  // 경도 소수점 2자리 반올림

        alert(`랜덤 위치가 업데이트되었습니다! 새로운 위치: 위도: ${roundedLatitude}, 경도: ${roundedLongitude}`);

        // 업데이트된 위치를 person에 반영
        setPerson((prevPerson) => ({
          ...prevPerson,
          lastSeenPlace: `위도: ${roundedLatitude}, 경도: ${roundedLongitude}`,
        }));
      })
      .catch((err) => {
        console.error(err);
        alert("위치 업데이트에 실패했습니다.");
      });
  };

  if (!person) return <p>로딩 중...</p>;

  return (
    <div className="elderly-detail-container">
      <div className="header">
        <button className="back-btn" onClick={() => navigate("/elderly")}>
          ← 목록으로
        </button>
        <h2 className="title">상세 정보</h2>
        <div>
          <button className="edit-btn" onClick={() => navigate(`/elderly/${id}/edit`)}>
            수정
          </button>
          <button className="delete-btn" onClick={handleDelete}>
            삭제
          </button>
        </div>
      </div>

      <div className="detail-content">
        <p><strong>이름:</strong> {person.name}</p>
        <p><strong>나이:</strong> {person.age}</p>
        <p><strong>치매 수준:</strong> {person.dementiaLevel}</p>
        <p><strong>보호자:</strong> {person.guardianName}</p>
        <p><strong>전화번호:</strong> {person.guardianPhone}</p>
        <p><strong>최근 위치:</strong> {person.lastSeenPlace}</p>
        <p><strong>상태:</strong> {person.status}</p>
      </div>

      <div className="gps-container">
        <button onClick={handleStartRandomGPS} className="start-random-gps-btn">
          GPS 시작하기
        </button>
        {location && (
          <p>
            <strong>현재 위치:</strong> {location.latitude}, {location.longitude}
          </p>
        )}
      </div>
    </div>
  );
}
