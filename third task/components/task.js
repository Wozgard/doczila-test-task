const taskList = document.getElementById("task-list");

export const fetchTasks = async () => {
  const response = await fetch("/fetchData");
  renderTasks(await response.json());
};

export const renderTasks = async (tasks) => {
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
    <div class="task-list__task task">
      <div class="task__left-part">
        <h2 class="task__header">${task.name}</h2>
        <div class="task__description">${task.shortDesc}</div>
      </div>
      <div class="task__right-part">
        <input class="task__checkbox" type="checkbox" ${isChecked} />
        <div class="task__date">${taskDate}</div>
      </div>
    </div>

    `;
  });

  taskList.innerHTML = tasksHTML;
};

export const loadTasks = async () => {
  const loaderHTML = `
  <div class="loader-container">
    <div class="loader"></div>
  </div>
  `;
  taskList.innerHTML = loaderHTML;
};
