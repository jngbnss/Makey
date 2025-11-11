import "./GameBoard.css";

export default function GameBoard({ game, onNextRound, onBack, onHome, isFinished }) {
  const sortedPlayers = [...game.players].sort((a, b) => b.score - a.score);
  const winner = sortedPlayers[0];

  return (
    <div className="game-container">
      {!isFinished && <h2>라운드 {game.currentRound} / {game.rounds}</h2>}

      <div className="tracks-container">
        {game.players.map((p) => {
          const percent = (p.score / game.rounds) * 100;

          return (
            <div key={p.id} className="player-track">
              <span className="player-name">{p.name}</span>

              <div className="track-box">
                {/* ✅ 이동 흔적 */}
                <div
                  className="track-progress"
                  style={{ right: 0, width: `${percent}%` }}
                />

                {/* ✅ 작은 자동차 + 트랙 안에서 이동 */}
                <div
                  className="car"
                  style={{ right: `${percent}%` }}
                >
                  🏎️
                </div>
              </div>

              {/* ✅ 주사위 표시 */}
              <div className="dice-box">
                🎲 {p.dice ?? "-"}
              </div>
            </div>
          );
        })}
      </div>

      {!isFinished && (
        <div className="controls">
          <button onClick={onNextRound} className="next-round-btn">주사위 굴리기</button>
          <button onClick={onBack} className="restart-btn">게임 다시하기</button>
        </div>
      )}

      {isFinished && (
        <div className="finish-screen">
          <h2>게임 종료!</h2>
          <h3>🏆 우승자: {winner.name} 🏆</h3>
          <h4>최종 순위:</h4>
          <ol>
            {sortedPlayers.map((p) => (
              <li key={p.id}>{p.name} - 점수: {p.score}</li>
            ))}
          </ol>
          <div className="finish-buttons">
            <button onClick={onHome} className="home-btn">홈으로 돌아가기</button>
            <button onClick={onBack} className="restart-btn">게임 다시하기</button>
          </div>
        </div>
      )}
    </div>
  );
}
