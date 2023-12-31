# doczila-test-task
Это тестовое задание в компанию Doczilla на позицию Junior

Оглавление:
- [Задание](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5)
    - [Задание 1](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-1)
    - [Задание 2](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-2)
    - [Задание 3](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-3)
- [Инструкция по запуску](https://github.com/Wozgard/doczila-test-task#%D0%B8%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%86%D0%B8%D1%8F-%D0%BF%D0%BE-%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA%D1%83)
    - [Задание 1](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-1-1)
    - [Задание 2](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-2-1)
    - [Задание 3](https://github.com/Wozgard/doczila-test-task#%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-3-1)


## Задание
### **Задание #1**

Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находится текстовые файлы и папки (уровень вложенности может оказаться любым). Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.

В каждом файле может быть ни одной, одна или несколько директив формата:

*require ‘<путь к другому файлу от корневого каталога>’*

Директива означает, что текущий файл зависит от другого указанного файла. Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: если файл А, зависит от файла В, то файл А находится ниже файла В в списке. Осуществить конкатенацию файлов в соответствии со списком. Если такой список построить невозможно (существует циклическая зависимость), программа должна вывести соответствующее сообщение. В случае циклической зависимости вывести объяснение ошибки - указать цикл зависимостей между файлами.

☝ **Реализация должна быть написана на любом из следующих языков: C, C++, Java, Kotlin, Rust**

➡️ Пример. Дана структура файлов и каталогов:

- Каталог “Folder 1”
    - Файл “File 1-1”. Содержимое:
    
    `Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id enim euismod erat elementum cursus. In hac habitasse platea dictumst. Etiam vitae tortor ipsum. Morbi massa augue, lacinia sed nisl id, congue eleifend lorem.`
    
    ***`require ‘Folder 2/File 2-1’`***
    
    `Praesent feugiat egestas sem, id luctus lectus dignissim ac. Donec elementum rhoncus quam, vitae viverra massa euismod a. Morbi dictum sapien sed porta tristique. Donec varius convallis quam in fringilla.`
    
- Каталог “Folder 2”
    - Файл “File 2-1”. Содержимое:
    
    `Phasellus eget tellus ac risus iaculis feugiat nec in eros. Aenean in luctus ante. In lacinia lectus tempus, rutrum ipsum quis, gravida nunc. Fusce tempor eleifend libero at pharetra. Nulla lacinia ante ac felis malesuada auctor. Vestibulum eget congue sapien, ac euismod elit. Fusce nisl ante, consequat et imperdiet vel, semper et neque.`
    
    - Файл “File 2-2”. Содержимое:
    
    ***`require ‘Folder 1/File 1-1’`***
    
    ***`require ‘Folder 2/File 2-1’`***
    
    `In pretium dictum lacinia. In rutrum, neque a dignissim maximus, dolor mi pretium ante, nec volutpat justo dolor non nulla. Vivamus nec suscipit nisl, ornare luctus erat. Aliquam eget est orci. Proin orci urna, elementum a nunc ac, fermentum varius eros. Mauris id massa elit.`
    

💡 Для указанной структуры каталогов и файлов должен быть построен список:

Folder 2/File 2-1

Folder 1/File 1-1

Folder 2/File 2-2

### **Задание #2**

Для выполнения задания выбери любую реляционную базу данных (oracle, sql server, mysql, postgresql, sqlite и т.д.).

1. Заведи в БД таблицу данных о студентах, которая будет содержать: имя, фамилия, отчество, дата рождения, группа, уникальный номер.
2. Создай веб-приложение (клиентскую и серверную части), с помощью которого можно добавить студента, удалить студента по уникальному номеру, вывести список студентов.

☝ **Реализация серверной части должна быть написана на любом из следующих языков: C, C++, Java, Kotlin, Rust**

☝ **Реализация клиентской части должна быть написана на JavaScript без использования сторонних библиотек и фреймворков. Исключением является jQuery.**

**☝ При работе с БД используй ручное написание запросов, а не ORM. Примени технологию REST API.**

### **Задание #3**

Напиши Todo List в соответствии с [макетом](https://www.figma.com/file/vBoI7L0RN55cH4pB9TTShS/%D0%9C%D0%B0%D0%BA%D0%B5%D1%82-%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D1%8B-%D0%B4%D0%BB%D1%8F-%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F-3?node-id=0%3A1)  (макет приблизительный, ты волен придумать свое дизайнерское решение).

☝ Для выполнения задания, вам потребуется обращаться к серверу посредством API. Ознакомьтесь с [документацией](https://todo.doczilla.pro/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config).

☝ **В ходе выполнения помимо JavaScript из сторонних библиотек можно использовать только jQuery и jQuery UI.**

**☝ Возможно, понадобится написать серверную часть для обхода CORS-ограничений. Эту часть решения можно писать на любом ЯП.**

☝ Что должно быть реализовано:

1. Поиск задач по названию.
2. Календарь с возможностью выбора даты.
3. Кнопка для вывода задач на сегодняшнюю (текущую) дату.
4. Кнопка для вывода задач на текущую неделю.
5. Возможность сортировать список задач по дате.
6. Возможность вывода только невыполненных задач.
7. Возможность открывать полное описание задачи (например, в модальном окне).
8. В календаре можно выбрать диапазон дат.
9. Поиск с выпадающим списком найденных задач, по нажатии на элементы которого открывается полное описание задачи.

🤓 Как мы будем оценивать выполненные задания? Мы будем обращать внимание на полноту решения задания (1), чистоту кода (2), понимание принципов ООП (3) и работу с GitHub (4).

Решения будем ожидать на Github с README файлом, в котором будет краткое описание и инструкция по запуску.

Совет: разбей задание на отдельные коммиты, не надо пушить в репозиторий сразу финальную версию.

Удачи!

# **Инструкция по запуску**
## Задание 1
Для него нужно иметь установленный JDK и на этом все. **Важно**, что запускать нужно файл Main.java из директории /doczila-test-task, не переходя по пути /doczila-test-task/first task

## Задание 2
Перед запуском необходимо добавить JAR-файлы из папки "jar files" в classpath. В VS code, в котором я работал это делается так:
1. Когда мы выбираем файл .java в самом низу файловой системы появляется окно Java project
2. В окне в самом низу есть Referenced Libraries
3. Нажимаем на + возле них и добавляем файлы

![image](https://github.com/Wozgard/doczila-test-task/assets/85787438/9f8be44b-647d-4be7-b653-f46370986fdd)

Также должен быть установлен и запущен PostgreSQL. В строках 23-24 файла Server.java необходимо изменить на свои данные пользователя postres

![image](https://github.com/Wozgard/doczila-test-task/assets/85787438/c7e1e0ba-c649-4817-ac21-09cdb2574e33)

Запуск также лучше осуществлять из папки /doczila-test-task, а не /doczila-test-task/second task

## Задание 3
Первым делом переходим внутрь папки /doczila-test-task/third task, затем прописываем следующие команды:
```bash
npm i
```
```bash
npm start
```
Все, наш сервер стартовал на порту 3000, можно проверять работу сайта

**Кстати**, к сожалению в API Doczill'ы нет задач на 2023 год. Поэтому, чтобы полноценно проверить работу Datepicker и других функций, связанных с датой придется отмотать на 2022, к примеру 8 октября 2022, там есть как выполненные, так и невыполненные задачи, поэтому этот день отлично подходит для проверки
