import React, { useRef, useEffect } from "react";
import { Timeline, DataSet } from "vis-timeline/standalone";
import "./GanttChart.css";

const GanttChart = () => {
  const timelineRef = useRef(null);

  // Define fetchData outside of useEffect
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

  useEffect(() => {
    const loadChartData = async () => {
      const projectId = 0; // Replace with the actual project ID

      try {
        // Fetch tasks and epics in parallel
        const [tasks, epics] = await Promise.all([
          fetchData(`${process.env.REACT_APP_ALL_TASKS_URL}/${projectId}`),
          fetchData(process.env.REACT_APP_ALL_EPICS_URL),
        ]);

        // Map data to items for the timeline
        const items = new DataSet(
          tasks
            .map((task) => ({
              id: task.taskId,
              content: task.title,
              start: task.startDate || task.createdAt,
              group: task.epicId,
              end: task.dueDate,
            }))
            .filter((item) => item.start) // Filter out items without a start date
        );

        // Map data from epics to groups for the Gantt chart
        console.log("Epics before mapping:", epics);
        const groups = epics.map((epic) => ({
          id: epic.epicId,
          content: epic.title,
        }));
        console.log("Groups after mapping:", groups);

        // Configuration for the Timeline
        const options = {};

        // Create a Timeline
        if (timelineRef.current) {
          const timeline = new Timeline(
            timelineRef.current,
            items,
            groups,
            options
          );

          // TODO: Add event listener for item clicks if needed
          // timeline.on('select', (properties) => {
          //   const selectedItemId = properties.items[0];
          //   // Handle item click, e.g., navigate to task details page
          // });
        }
      } catch (error) {
        console.error("Error loading chart data:", error);
      }
    };

    loadChartData();
  }, []); // Empty dependency array to run only once

  return <div className="chartContainer" ref={timelineRef}></div>;
};

export default GanttChart;
