import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/game"
});

export const startGame = (rounds, playerNames) =>
  api.post("/start", { rounds, playerNames });

export const playRound = (gameId) =>
  api.post(`/${gameId}/round`);
