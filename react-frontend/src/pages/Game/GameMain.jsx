import { useState } from "react";
import SetupForm from "../../components/Game/SetupForm";
import GameBoard from "../../components/Game/GameBoard";

export default function GameMain() {
  const [game, setGame] = useState(null);

  const startGame = async (setup) => {
    const res = await fetch("http://localhost:8080/api/game/start", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(setup),
    });
    const data = await res.json();
    setGame(data);
  };

  const nextRound = async () => {
    if (!game) return;
    const res = await fetch(`http://localhost:8080/api/game/${game.id}/round`, {
      method: "POST",
    });
    const data = await res.json();
    setGame(data);
  };

  return (
    <div style={{ padding: "20px" }}>
      {!game && <SetupForm onStart={startGame} />}
      {game && <GameBoard game={game} onNextRound={nextRound} />}
    </div>
  );
}
