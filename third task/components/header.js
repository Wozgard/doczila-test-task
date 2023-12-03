import { renderTasks, loadTasks } from "./task.js";

const header = document.getElementById("header");

const headerHTML = `
<header class="header">
  <div class="header__container container">
    <div class="header__search">
      <img
        class="header__search-image"
        src="./public/search_icon.svg"
        alt="search icon"
      />
      <input class="header__search-input" type="text" placeholder="Поиск" />
    </div>
    <div class="header__avatar">
      <img
        class="header__avatar-image"
        src="./public/avatar_icon.svg"
        alt="avatar icon"
      />
    </div>
  </div>
</header>
`;

// Заменяем содержимое элемента новым HTML кодом
if (header) {
  header.innerHTML = headerHTML;
} else {
  console.error('Элемент с id "header" не найден.');
}

export const searchListener = () => {
  const inputElement = document.querySelector(".header__search-input");

  let timerId;

  // Функция для отправки запроса
  const sendRequest = async (value) => {
    const response = await fetch(`/findTask?q=${value}`);
    const tasks = await response.json();
    renderTasks(tasks);
  };

  // Обработчик события input
  inputElement.addEventListener("input", function (event) {
    // Очищаем таймер при каждом вводе
    clearTimeout(timerId);

    const value = event.target.value;

    // Устанавливаем таймер на 3 секунды после окончания ввода
    timerId = setTimeout(() => {
      sendRequest(value);
      loadTasks();
    }, 500);
  });
};
