// src/pages/Main.jsx
import { useNavigate } from "react-router-dom";
import Card from "../components/Card";

export default function Main() {
  const navigate = useNavigate();

  const cards = [
    { id: 1, title: "프로필", route: "/profile" },
    { id: 2, title: "게시판", route: "/board" },
    { id: 3, title: "3", route: "#" },
  ];

  return (
    <div style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gap: "20px", padding: "20px" }}>
      {cards.map(card => (
        <Card
          key={card.id}
          item={card}
          onClick={() => {
            console.log("clicked", card.route);
            if (card.route !== "#") navigate(card.route);
          }}
        />
      ))}
    </div>
  );
}
