// Получаем элемент с id "header"
const header = document.getElementById("header");

// Создаем новый HTML код
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
