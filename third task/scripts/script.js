import { fetchTasks, loadTasks, renderTasks } from "../components/task.js";
import { searchListener } from "../components/header.js";
import { checkboxListener } from "../components/sidebar.js";

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
});


export const whenDateSelected = (dateText) => {
  const date = new Date(dateText);
  setSelectedDate(date);
  loadTasks();

  const statusCheckbox = document.querySelector("#status-checkbox");
  if (statusCheckbox.checked) {
    findTaskByDate(date, true);
  }
  else {
    findTaskByDate(date);
  }
}

const setSelectedDate = (date) => {
  const formatedDate = new Intl.DateTimeFormat("ru-RU", {
    year: "numeric",
    month: "long",
    day: "numeric",
  }).format(date);
  $("#selected-date").html(`${formatedDate}`);
};

const findTaskByDate = async (date, findByStatus = false) => {
  const startOfDay = new Date(date);
  startOfDay.setHours(0, 0, 0, 0);

  const endOfDay = new Date(date);
  endOfDay.setHours(23, 59, 59, 999);

  const response = await fetch(`/findTaskByDate?from=${startOfDay.valueOf()}&to=${endOfDay.valueOf()}&findByStatus=${findByStatus}`); 
  renderTasks(await response.json());
}

