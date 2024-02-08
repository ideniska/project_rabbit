import React, { useState } from "react";

const TaskForm = () => {
  const [title, setTitle] = useState("");
  const [status, setStatus] = useState(1);
  const [project, setProject] = useState(1);
  const handleSubmit = async (event) => {
    event.preventDefault();
    const taskData = {
      title,
      status: parseInt(status, 10), // Convert status to a number
      project: parseInt(project, 10), // Convert project to a number
    };

    try {
      const response = await fetch(process.env.REACT_APP_ADD_TASK_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(taskData),
      });

      if (response.ok) {
        const result = await response.json();
        console.log("Task created:", result);
        // Handle success (maybe clear the form or show a success message)
      } else {
        // Handle errors (response not ok)
        console.error("Failed to create task:", response.statusText);
      }
    } catch (error) {
      // Handle network errors
      console.error("Network error:", error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="title">Task Title:</label>
      <input
        type="text"
        id="title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        required
      />

      <label htmlFor="status">Status:</label>
      <input
        type="text"
        id="status"
        value={status}
        onChange={(e) => setStatus(e.target.value)}
        required
      />

      <label htmlFor="project">Project:</label>
      <input
        type="text"
        id="project"
        value={project}
        onChange={(e) => setProject(e.target.value)}
        required
      />

      <button type="submit">Create Task</button>
    </form>
  );
};

export default TaskForm;
