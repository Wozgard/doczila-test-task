import { fetchTasks, loadTasks } from "../components/task.js";
import { searchListener } from "../components/header.js";

document.addEventListener("DOMContentLoaded", async function () {
  setSelectedDate(new Date());

  $("#datepicker").datepicker({
    inline: true,
    onSelect: function (dateText) {
      const date = new Date(dateText);
      setSelectedDate(date);
    },
  });

  // for task list
  loadTasks();
  fetchTasks();

  // for search
  searchListener();
});

const setSelectedDate = (date) => {
  const formatedDate = new Intl.DateTimeFormat("ru-RU", {
    year: "numeric",
    month: "long",
    day: "numeric",
  }).format(date);
  $("#selected-date").html(`<span>${formatedDate}</span>`);
};
