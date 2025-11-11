import { useState } from "react";
import "./SetupForm.css";

export default function SetupForm({ onStart }) {
  const [rounds, setRounds] = useState(5);
  const [playerNames, setPlayerNames] = useState([""]);

  const handleAddPlayer = () => {
    if (playerNames.length < 5) setPlayerNames([...playerNames, ""]);
  };

  const handleChangeName = (index, value) => {
    const names = [...playerNames];
    names[index] = value;
    setPlayerNames(names);
  };

  const handleStart = () => {
    const filteredNames = playerNames.filter((n) => n.trim() !== "");
    if (filteredNames.length === 0) return alert("최소 1명 이상의 플레이어 필요");

    onStart({ rounds, playerNames: filteredNames });
  };

  return (
    <div className="setup-container">
      <div className="setup-form">
        <h2>🏎️ 레이싱 게임 설정</h2>

        <div>
          <label>라운드 수: </label>
          <input
            type="number"
            value={rounds}
            min={1}
            onChange={(e) => setRounds(Number(e.target.value))}
          />
        </div>

        <div className="player-list">
          {playerNames.map((name, idx) => (
            <input
              key={idx}
              value={name}
              onChange={(e) => handleChangeName(idx, e.target.value)}
              placeholder={`플레이어 ${idx + 1}`}
            />
          ))}
          {playerNames.length < 5 && (
            <button onClick={handleAddPlayer}>플레이어 추가</button>
          )}
        </div>

        <button onClick={handleStart}>게임 시작</button>
      </div>
    </div>
  );
}
