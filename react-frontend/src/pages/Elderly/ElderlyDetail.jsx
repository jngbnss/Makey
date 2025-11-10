import { useState, useEffect, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./ElderlyDetail.css";

export default function ElderlyDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const mapRef = useRef(null);
  const [map, setMap] = useState(null);
  const [marker, setMarker] = useState(null);
  const [person, setPerson] = useState({
    name: "",
    age: 0,
    dementiaLevel: "",
    guardianName: "",
    guardianPhone: "",
    lastSeenPlace: "위도: 37.5665, 경도: 126.978",
    status: "",
  });

  // 실제 데이터 가져오기
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/persons/${id}`)
      .then((res) => setPerson(res.data))
      .catch((err) => console.error(err));
  }, [id]);

  // 카카오맵 API 스크립트 로딩
  useEffect(() => {
    const initMap = () => {
      if (window.kakao && mapRef.current && !map) {
        const kakaoMap = new window.kakao.maps.Map(mapRef.current, {
          center: new window.kakao.maps.LatLng(37.5665, 126.978),
          level: 4,
        });

        setMap(kakaoMap);

        const initialMarker = new window.kakao.maps.Marker({
          position: kakaoMap.getCenter(),
          map: kakaoMap,
        });

        setMarker(initialMarker);
      }
    };

    // 백엔드에서 카카오맵 스크립트 URL 가져오기
    axios
      .get("http://localhost:8080/api/proxy/kakao-map")  // 스프링 서버로부터 URL 가져오기
      .then((res) => {
        const script = document.createElement("script");
        script.src = res.data;  // 백엔드에서 전달받은 카카오맵 URL
        script.async = true;

        script.onload = () => {
          console.log("✅ 카카오 맵 스크립트 로딩 완료");
          window.kakao.maps.load(initMap);
        };

        script.onerror = () => {
          console.error("❌ 카카오 맵 스크립트 로딩 실패");
        };

        document.head.appendChild(script);
      })
      .catch((err) => console.error("카카오 맵 API 로드 실패:", err));
  }, [map]);

  // lastSeenPlace 변경 시 마커 위치 이동
  useEffect(() => {
    if (!map || !marker || !person?.lastSeenPlace) return;

    const [latStr, lngStr] = person.lastSeenPlace
      .replace("위도:", "")
      .replace("경도:", "")
      .split(",")
      .map((s) => parseFloat(s.trim()));

    const newPosition = new window.kakao.maps.LatLng(latStr, lngStr);

    marker.setPosition(newPosition);
    map.setCenter(newPosition);
  }, [person?.lastSeenPlace, map, marker]);

  // ✅ 삭제
  const handleDelete = () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios.delete(`http://localhost:8080/api/persons/${id}`).then(() => {
        navigate("/elderly");
      });
    }
  };

  // ✅ 랜덤 GPS 이동
  const handleStartRandomGPS = () => {
    const randomLat = 37.5665 + (Math.random() - 0.5) * 0.02;
    const randomLng = 126.978 + (Math.random() - 0.5) * 0.02;

    setPerson((prev) => ({
      ...prev,
      lastSeenPlace: `위도: ${randomLat.toFixed(5)}, 경도: ${randomLng.toFixed(5)}`,
    }));

    alert(`랜덤 위치 업데이트!\n위도: ${randomLat.toFixed(5)}, 경도: ${randomLng.toFixed(5)}`);
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
          <button
            className="edit-btn"
            onClick={() => navigate(`/elderly/${id}/edit`)}
          >
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
      </div>

      {/* ✅ 지도 영역 */}
      <div ref={mapRef} className="gps-map"></div>
    </div>
  );
}
