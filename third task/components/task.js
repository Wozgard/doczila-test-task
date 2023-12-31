import { modalListener } from "../components/modal.js";


const taskList = document.getElementById("task-list");

export const fetchTasks = async () => {
  const response = await fetch("/fetchData");
  const tasks = await response.json();

  localStorage.setItem('tasks', JSON.stringify(tasks));
  renderTasks(tasks);
};

export const renderTasks = async (tasks) => {
  if (tasks.length > 0) {
    console.log(tasks);
    let tasksHTML = ``;
    tasks.forEach((task) => {
      const taskDate = new Intl.DateTimeFormat("ru-RU", {
        year: "numeric",
        month: "numeric",
        day: "numeric",
        hour: "numeric",
        minute: "numeric",
      }).format(new Date(task.date));

      const isChecked = task.status ? "checked" : "";

      tasksHTML += `
    <div class="task-list__task task _openModalBtn" data-modal=${task.id}>
      <div class="task__left-part">
        <h2 class="task__header">${task.name}</h2>
        <div class="task__description">${task.shortDesc}</div>
      </div>
      <div class="task__right-part">
        <input class="task__checkbox" type="checkbox" ${isChecked} />
        <div class="task__date">${taskDate}</div>
      </div>
    </div>

    <div id=${task.id} class="modal">
      <div class="modal-content">
        <span class="close">&times;</span>
        <h2 class="modal-title">${task.name}</h2>
        <p class="modal-description">${task.fullDesc}</p>
      </div>
    </div>

    `;
    });

    taskList.innerHTML = tasksHTML;
  } else {
    const loaderHTML = `
    <div class="loader-container">
      Извините, но по вашему запросу ничего не найдено :(
    </div>
    `;
    taskList.innerHTML = loaderHTML;
  }
  modalListener();
};

export const loadTasks = async () => {
  const loaderHTML = `
  <div class="loader-container">
    <div class="loader"></div>
  </div>
  `;
  taskList.innerHTML = loaderHTML;
};
