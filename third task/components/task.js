const taskList = document.getElementById("task-list");

const fetchTasks = async () => {
  const response = await fetch(
    "/fetchData",
    {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    }
  );
  return await response.json();
};

const renderTasks = async () => {
  const tasks = await fetchTasks();
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
renderTasks();