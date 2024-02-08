import "./Home.css";
import Backlog from "../Backlog/Backlog";

export default function Home({ sidebarWidth }) {
  return (
    <div>
      <p>Home page</p>
      <p>Backlog dashboard here</p>
      <Backlog/>
    </div>
  );
}
