import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import Sidebar from "./components/Sidebar/Sidebar";
import Home from "./pages/Home/Home";
import Board from "./pages/Board/Board";
import Timeline from "./pages/Timeline/Timeline";
import Profile from "./pages/Profile/Profile";
import ApiTestPage from "./pages/ApiTest/ApiTest";
import Backlog from "./pages/Backlog/Backlog";
import "./App.css";

const ContentWrapper = ({ children }) => {
  return <div className="content">{children}</div>;
};

// App component with routes
function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Sidebar />
        <Routes>
          <Route
            path="/"
            element={
              <ContentWrapper>
                <Home />
              </ContentWrapper>
            }
          ></Route>
          <Route
            path="/board"
            element={
              <ContentWrapper>
                <Board />
              </ContentWrapper>
            }
          ></Route>
          <Route
            path="/timeline"
            element={
              <ContentWrapper>
                <Timeline />
              </ContentWrapper>
            }
          ></Route>
          <Route
            path="/profile"
            element={
              <ContentWrapper>
                <Profile />
              </ContentWrapper>
            }
          ></Route>
          <Route
            path="/test-page"
            element={
              <ContentWrapper>
                <ApiTestPage />
              </ContentWrapper>
            }
          ></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
