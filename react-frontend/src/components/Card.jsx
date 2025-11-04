// src/components/Card.jsx
export default function Card({ item, onClick }) {
  return (
    <div
      onClick={onClick} 
      style={{
        width: "150px",
        height: "150px",
        backgroundColor: "#ffe0b2",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        borderRadius: "10px",
        cursor: item.route && item.route !== "#" ? "pointer" : "default",
        boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
        textAlign: "center",
        padding: "10px",
      }}
    >
      {item.title}
    </div>
  );
}
