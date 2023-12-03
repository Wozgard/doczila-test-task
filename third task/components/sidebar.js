import { whenDateSelected } from "../scripts/script.js";

const sidebar = document.getElementById("sidebar");

const sidebarHTML = `
<aside class="sidebar">
  <button class="sidebar__today btn">Сегодня</button>
  <button class="sidebar__this-week btn">На неделю</button>
  <div class="sidebar__datepicker" id="datepicker"></div>
  <div class="sidebar__pending">
    <input id="status-checkbox" class="sidebar__pending-input" type="checkbox" />
    <div class="sidebar__pending-text">Только невыполненные</div>
  </div>
</aside>
`;

// Заменяем содержимое элемента новым HTML кодом
if (sidebar) {
  sidebar.innerHTML = sidebarHTML;
} else {
  console.error('Элемент с id "sidebar" не найден.');
}

export const checkboxListener = () => {
  const checkbox = document.querySelector("#status-checkbox");
  checkbox.addEventListener("change", () => {
    const date = new Date(document.getElementById("datepicker").value);
    whenDateSelected(date);
  })
}