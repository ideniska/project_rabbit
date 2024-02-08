import React, { useState, useEffect } from "react";
import Board from "@asseinfo/react-kanban";
import "@asseinfo/react-kanban/dist/styles.css";

const projectId = 0; // Replace with the actual project ID

const fetchData = async (url) => {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Failed to fetch data:", error);
    return [];
  }
};

const KanbanBoard = () => {
  const [boardData, setBoardData] = useState(null); // Start with null to indicate no data
  const [isLoading, setIsLoading] = useState(true); // Start with loading true

  useEffect(() => {
    const loadKanbanData = async () => {
      setIsLoading(true); // Start loading
      try {
        const [tasks, epics] = await Promise.all([
          fetchData(`${process.env.REACT_APP_ALL_TASKS_URL}/${projectId}`),
          fetchData(process.env.REACT_APP_ALL_EPICS_URL),
        ]);

        // Process tasks into columns based on status
        const tasksToDo = tasks
          .filter((task) => task.status === 0)
          .map((task) => ({
            id: task.taskId,
            title: task.title,
            description: task.description,
          }));

        const tasksInProgress = tasks
          .filter((task) => task.status === 1)
          .map((task) => ({
            id: task.taskId,
            title: task.title,
            description: task.description,
          }));

        const tasksDone = tasks
          .filter((task) => task.status === 2)
          .map((task) => ({
            id: task.taskId,
            title: task.title,
            description: task.description,
          }));

        // Construct the columns array
        const columns = [
          {
            id: 1,
            title: "TO DO",
            cards: tasksToDo,
          },
          {
            id: 2,
            title: "IN PROGRESS",
            cards: tasksInProgress,
          },
          {
            id: 3,
            title: "DONE",
            cards: tasksDone,
          },
        ];

        setBoardData({ columns });
      } catch (error) {
        console.error("Failed to load kanban data:", error);
        setBoardData({ columns: [] }); // Set empty data on error
      }
      setIsLoading(false); // Stop loading
    };

    loadKanbanData();
  }, []);

  if (isLoading) {
    return <div>Loading...</div>; // Show loading indicator while data is being fetched
  }

  // Only render Board if boardData is not null
  return boardData ? <Board initialBoard={boardData} /> : null;
};

export default KanbanBoard;
