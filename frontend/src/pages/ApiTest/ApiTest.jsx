import { useEffect, useState } from "react";

export default function ApiTestPage() {
  const [allTasks, setAllTasks] = useState([]);
  const [allUsers, setAllUsers] = useState([]);

  const fetchData = async (url) => {
    try {
      const response = await fetch(url);
      console.log(response);
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      console.error("Failed to fetch data:", error);
      return [];
    }
  };

  useEffect(() => {
    const loadData = async () => {
      const tasks = await fetchData(process.env.REACT_APP_ALL_TASKS_URL);
      setAllTasks(tasks);
      const users = await fetchData(process.env.REACT_APP_ALL_USERS_URL);
      setAllUsers(users);
    };
    loadData();
  }, []);

  return (
    <div>
      <h2>Tasks</h2>
      <ul>
        {allTasks.map((task) => (
          <li key={task.taskId}>
            <strong>{task.title}</strong>
            <p>Description: {task.description || "No description"}</p>
            <p>Due Date: {task.dueDate || "No due date"}</p>
            <p>Created At: {task.createdAt}</p>
            <p>Project ID: {task.projectId}</p>
            <p>Epic ID: {task.epicId}</p>
            <p>Assigned To: {task.assignedTo}</p>
            <p>Status: {task.status}</p>
          </li>
        ))}
      </ul>

      <h2>Users</h2>
      <ul>
        {allUsers.map((user) => (
          <li key={user.userId}>
            <strong>{user.email}</strong>
            <strong>{user.firstName}</strong>
            <strong>{user.lastName}</strong>
          </li>
        ))}
      </ul>
    </div>
  );
}
