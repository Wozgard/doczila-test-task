import { fetchTasks, loadTasks, renderTasks } from "../components/task.js";
import { searchListener } from "../components/header.js";
import {
  checkboxListener,
  todayButtonListener,
  thisWeekButtonListener,
} from "../components/sidebar.js";

document.addEventListener("DOMContentLoaded", async function () {
  setSelectedDate(new Date());

  $("#datepicker").datepicker({
    inline: true,
    onSelect: function (dateText) {
      whenDateSelected(dateText);
    },
  });

  // for task list
  loadTasks();
  fetchTasks();

  // for search
  searchListener();

  // for date
  checkboxListener();
  todayButtonListener();
  thisWeekButtonListener();
  sortByDateListener();
});

export const whenDateSelected = (dateText, toDate = "") => {
  const date = new Date(dateText);
  setSelectedDate(date, toDate);
  loadTasks();

  const statusCheckbox = document.querySelector("#status-checkbox");
  if (statusCheckbox.checked) {
    findTaskByDate(date, toDate, true);
  } else {
    findTaskByDate(date, toDate);
  }
};

const setSelectedDate = (date, toDate = "") => {
  const formatedDate = new Intl.DateTimeFormat("ru-RU", {
    year: "numeric",
    month: "long",
    day: "numeric",
  }).format(date);

  if (toDate === "") {
    $("#selected-date").html(`${formatedDate}`);
  }
  else {
    const formatedToDate = new Intl.DateTimeFormat("ru-RU", {
      year: "numeric",
      month: "long",
      day: "numeric",
    }).format(toDate);
    $("#selected-date").html(`${formatedDate} - ${formatedToDate}`);
  }
};

const findTaskByDate = async (date, toDate = "", findByStatus = false) => {
  toDate === "" ? (toDate = date) : (toDate = toDate);

  const startOfDay = new Date(date);
  startOfDay.setHours(0, 0, 0, 0);

  const endOfDay = new Date(toDate);
  endOfDay.setHours(23, 59, 59, 999);

  const response = await fetch(
    `/findTaskByDate?from=${startOfDay.valueOf()}&to=${endOfDay.valueOf()}&findByStatus=${findByStatus}`
  );


  const tasks = await response.json();

  localStorage.setItem('tasks', JSON.stringify(tasks));
  renderTasks(tasks);
};


// Сортировка задач по дате ==================
const sortByDateListener = () => {
  const sortButton = document.querySelector(".task-list__sort-by-date");
  sortButton.addEventListener("click", () => {
    sortByDate();
  });
};

const sortByDate = () => {
  const tasksArray = JSON.parse(localStorage.getItem('tasks'));

  const newTaskArray = tasksArray.sort((a, b) => {
    const dateA = new Date(a.date);
    const dateB = new Date(b.date);
    return dateB - dateA;
  });

  renderTasks(newTaskArray);
};