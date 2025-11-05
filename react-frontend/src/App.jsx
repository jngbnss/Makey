import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PostList from "./pages/Post/PostList";  // 게시판 페이지
import Main from "./pages/Main";               // 메인 페이지
import ElderlyMain from "./pages/Elderly/ElderlyMain";  // 노인 페이지
import './App.css';                           // 스타일 파일

function App() {
  return (
    <Router>
      <Routes>
        {/* 메인 페이지 */}
        <Route path="/" element={<Main />} />

        {/* 게시판 관련 */}
        <Route path="/posts" element={<PostList />} />  {/* 게시판 페이지 */}

        {/* 노인 관리 페이지 */}
        <Route path="/elderly" element={<ElderlyMain />} />  {/* 노인 관리 페이지 */}
      </Routes>
    </Router>
  );
}

export default App;
