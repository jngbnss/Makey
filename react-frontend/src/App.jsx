// App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// 페이지 임포트
import Main from "./pages/Main";                        // 메인 페이지
import PostList from "./pages/Post/PostList";          // 게시판 페이지
import ElderlyMain from "./pages/Elderly/ElderlyMain"; // 노인 목록 페이지
import ElderlyRegister from "./pages/Elderly/ElderlyRegister"; // 등록 페이지
import ElderlyDetail from "./pages/Elderly/ElderlyDetail";     // 상세보기 페이지
import ElderlyEdit from "./pages/Elderly/ElderlyEdit";         // 수정 페이지
import GameMain from "./pages/Game/GameMain";  // 새로 추가
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/* 메인 페이지 */}
        <Route path="/" element={<Main />} />

        {/* 게시판 페이지 */}
        <Route path="/posts" element={<PostList />} />

        {/* 노인 관리 관련 */}
        <Route path="/elderly" element={<ElderlyMain />} />           {/* 목록 */}
        <Route path="/elderly/new" element={<ElderlyRegister />} />   {/* 등록 */}
        <Route path="/elderly/:id" element={<ElderlyDetail />} />     {/* 상세보기 */}
        <Route path="/elderly/:id/edit" element={<ElderlyEdit />} />  {/* 수정 */}
        {/* 게임 페이지 */}
        <Route path="/game" element={<GameMain />} />
      </Routes>
    </Router>
  );
}

export default App;
