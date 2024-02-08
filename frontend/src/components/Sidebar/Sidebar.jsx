import "./Sidebar.css";

export default function Sidebar() {
  return (
    <div className="sidebar">
      <ul>
        <li>
          <a href="/board">Kanban Board</a>
        </li>
        <li>
          <a href="/timeline">Gantt Chart</a>
        </li>
        <li>
          <a href="/">Backlog</a>
        </li>
      </ul>
    </div>
  );
}
