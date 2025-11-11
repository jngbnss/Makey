import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// 페이지 임포트
import Main from "./pages/Main";                        
import PostList from "./pages/Post/PostList";          
import ElderlyMain from "./pages/Elderly/ElderlyMain"; 
import ElderlyRegister from "./pages/Elderly/ElderlyRegister"; 
import ElderlyDetail from "./pages/Elderly/ElderlyDetail";     
import ElderlyEdit from "./pages/Elderly/ElderlyEdit";         
import GameMain from "./pages/Game/GameMain";  

import "./styles/App.css";

function App() {
  return (
    <Router>
      <Routes>
        {/* 메인 페이지 */}
        <Route path="/" element={<Main />} />

        {/* 게시판 페이지 */}
        <Route path="/posts" element={<PostList />} />

        {/* 노인 관리 관련 */}
        <Route path="/elderly" element={<ElderlyMain />} />           
        <Route path="/elderly/new" element={<ElderlyRegister />} />   
        <Route path="/elderly/:id" element={<ElderlyDetail />} />     
        <Route path="/elderly/:id/edit" element={<ElderlyEdit />} />  

        {/* 게임 페이지 */}
        <Route path="/game" element={<GameMain />} />
      </Routes>
    </Router>
  );
}

export default App;
