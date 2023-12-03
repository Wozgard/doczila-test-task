const express = require("express");
const axios = require("axios");
const path = require("path");
const url = require('url');
const querystring = require('querystring');

const app = express();
const PORT = 3000;

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  next();
});

// Обработка корневого пути - возврат HTML файла
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "index.html")); // Путь к вашему HTML файлу
});

app.use("/components", express.static(path.join(__dirname, "components")));
app.use("/styles", express.static(path.join(__dirname, "styles")));
app.use("/public", express.static(path.join(__dirname, "public")));
app.use("/scripts", express.static(path.join(__dirname, "scripts")));

app.get("/fetchData", async (req, res) => {
  try {
    const response = await axios.get(
      "https://todo.doczilla.pro/api/todos?limit=10&offset=0"
    );
    res.json(response.data);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

app.get("/findTask", async (req, res) => {
  try {
    const parsedUrl = url.parse(req.url);

    const queryParams = querystring.parse(parsedUrl.query);

    const searchTerm = queryParams.q;

    const response = await axios.get(
      `https://todo.doczilla.pro/api/todos/find?q=${searchTerm}&limit=10`
    );
    res.json(response.data);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

app.get("/findTaskByDate", async (req, res) => {
  try {
    const parsedUrl = url.parse(req.url);

    const queryParams = querystring.parse(parsedUrl.query);

    const fromDate = queryParams.from;
    const toDate = queryParams.to;
    const findByStatus = queryParams.findByStatus === 'true';
    const status = findByStatus ? "&status=false" : '';

    const response = await axios.get(
      `https://todo.doczilla.pro/api/todos/date?from=${fromDate}&to=${toDate}${status}&limit=10`
    );
    res.json(response.data);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
})

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
  console.log(`http://localhost:${PORT}`);
});
