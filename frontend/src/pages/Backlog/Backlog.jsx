import React, { useState, useEffect } from 'react';
import "./Backlog.css";


export default function Backlog() {
    const [tasks, setTasks] = useState([]);
    const [description, setDescription] = useState('');
    const [owner, setOwner] = useState('');
  
    useEffect(() => {
      loadTasks();
    }, []);
  
    const createTask = (taskId, description, owner, status) => (
      <li key={taskId} className="task-item">
        <span>Task ID: {taskId}</span>
        <span>Description: {description}</span>
        <span>Owner: {owner}</span>
        <div className="status-dropdown">
          <select
            className="status-select"
            value={status}
            onChange={(e) => updateTaskStatus(taskId, e.target.value)}
          >
            <option value="to-do">To Do</option>
            <option value="in-progress">In Progress</option>
            <option value="done">Done</option>
          </select>
        </div>
        <div className="task-actions">
          <button onClick={() => editTask(taskId)}>Edit</button>
          <button onClick={() => removeTask(taskId)}>Delete</button>
        </div>
      </li>
    );
  
    const updateTaskStatus = (taskId, newStatus) => {
      const updatedTasks = tasks.map((task) =>
        task.taskId === taskId ? { ...task, status: newStatus } : task
      );
      saveTasks(updatedTasks);
    };
  
    const addTask = () => {
      const taskId = tasks.length + 1;
      const status = 'to-do';
  
      const task = { taskId, description, owner, status };
      const updatedTasks = [...tasks, task];
      saveTasks(updatedTasks);
  
      // Clear input fields after adding a task
      setDescription('');
      setOwner('');
    };
  
    const removeTask = (taskId) => {
      const updatedTasks = tasks.filter((task) => task.taskId !== taskId);
      saveTasks(updatedTasks);
    };
  
    const editTask = (taskId) => {
      const updatedDescription = prompt('Enter updated task description:', description);
      const updatedOwner = prompt('Enter updated task owner:', owner);
  
      const updatedTask = {
        description: updatedDescription || description,
        owner: updatedOwner || owner,
      };
  
      const updatedTasks = tasks.map((task) =>
        task.taskId === taskId ? { ...task, ...updatedTask } : task
      );
  
      saveTasks(updatedTasks);
    };
  
    const saveTasks = (updatedTasks) => {
      localStorage.setItem('tasks', JSON.stringify(updatedTasks));
      setTasks(updatedTasks);
    };
  
    const loadTasks = () => {
      const savedTasks = localStorage.getItem('tasks');
      if (savedTasks) {
        setTasks(JSON.parse(savedTasks));
      }
    };
  
    return (
      <div>
        <div className="container">
          <h1>Tasks in Progress</h1>
          <ul>{tasks.map((task) => task.status !== 'done' && createTask(task.taskId, task.description, task.owner, task.status))}</ul>
          <button onClick={addTask}>Add Task</button>
        </div>
        <div className="container">
          <h1>Completed Tasks</h1>
          <ul>{tasks.map((task) => task.status === 'done' && createTask(task.taskId, task.description, task.owner, task.status))}</ul>
        </div>
      </div>
    );
}
