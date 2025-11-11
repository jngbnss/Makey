import { useState } from "react";
import { useNavigate } from "react-router-dom";
import SetupForm from "./SetupForm";
import GameBoard from "./GameBoard";

export default function GameMain() {
  const [game, setGame] = useState(null);
  const [page, setPage] = useState("setup");
  const navigate = useNavigate();

  // ✅ 게임 시작
  const startGame = (setup) => {
    const newGame = {
      rounds: setup.rounds,
      currentRound: 1,
      players: setup.playerNames.map((name, idx) => ({
        id: idx,
        name,
        score: 0,
        dice: null,
      })),
    };
    setGame(newGame);
    setPage("game");
  };

  // ✅ 플레이어 이동 (한 칸만 움직임)
  const movePlayer = (playerIndex, steps, callback) => {
    if (steps === 0) {
      if (callback) callback();
      return;
    }

    setGame(prev => {
      const players = [...prev.players];
      players[playerIndex].score = Math.min(players[playerIndex].score + 1, prev.rounds);
      return { ...prev, players };
    });

    setTimeout(() => callback && callback(), 300);
  };

  // ✅ 다음 라운드 (4 이상일 때만 이동 = 1칸)
  const nextRound = () => {
    if (!game) return;

    let finishedCount = 0;
    const totalPlayers = game.players.length;

    game.players.forEach((p, idx) => {
      const dice = Math.floor(Math.random() * 6) + 1;
      const steps = dice >= 4 ? 1 : 0;

      setGame(prev => {
        const players = [...prev.players];
        players[idx].dice = dice; // ✅ 주사위 결과 저장
        return { ...prev, players };
      });

      movePlayer(idx, steps, () => {
        finishedCount++;
        if (finishedCount === totalPlayers) {
          setGame(prev => ({ ...prev, currentRound: prev.currentRound + 1 }));
        }
      });
    });
  };

  // 뒤로 가기
  const backToHome = () => {
    navigate("/");
  };

  // 재시작
  const restartGame = () => {
    setGame(null);
    setPage("setup");
  };

  if (page === "setup") return <SetupForm onStart={startGame} />;
  if (page === "game")
    return (
      <GameBoard
        game={game}
        onNextRound={nextRound}
        onBack={restartGame}
        onHome={backToHome}
        isFinished={game.currentRound > game.rounds}
      />
    );

  return null;
}
