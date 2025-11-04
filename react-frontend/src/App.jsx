// App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ProfileList from "./pages/ProfileList";  // 프로필 목록 페이지
import Profile from "./pages/Profile";          // 프로필 상세 페이지
import Main from "./pages/Main";                // 메인 페이지
import './App.css';                            // 스타일 파일 불러오기

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/profile" element={<ProfileList />} />
        <Route path="/profile/:id" element={<Profile />} />
      </Routes>
    </Router>
  );
}

export default App;
