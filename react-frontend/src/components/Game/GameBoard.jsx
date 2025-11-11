export default function GameBoard({ game, onNextRound }) {
  return (
    <div>
      <h2>라운드 {game.currentRound} / {game.rounds}</h2>
      <div>
        {game.players.map((p) => (
          <div key={p.id}>
            {p.name}: {"▶".repeat(p.position)}
          </div>
        ))}
      </div>
      {!game.isFinished && <button onClick={onNextRound}>다음 라운드</button>}
      {game.isFinished && <div>게임 종료!</div>}
    </div>
  );
}
