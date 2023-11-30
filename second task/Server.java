import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Server {
  private static String jdbcUrl = "jdbc:postgresql://localhost:5432/";
  private static String username = "postgres";
  private static String password = "6432";
  private static String dbName = "studentsdb";

  public static DatabaseHandler dataHendler = new DatabaseHandler(jdbcUrl, username, password);

  public static StudentTableHandler studentTable = new StudentTableHandler(jdbcUrl, username, password);;

  public static void main(String[] args) throws IOException {
    dataHendler.createDatabase(dbName);
    studentTable.createStudentTable(dbName);

    int serverPort = 3000;
    HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

    server.createContext("/", new RootHandler());
    server.createContext("/styles.css", new CSSHandler());
    server.createContext("/script.js", new ScriptHandler());
    server.createContext("/students-list", new StudentsListHandler());
    server.createContext("/create-student", new CreateStudentHandler());
    server.createContext("/delete-student", new DeleteStudentHandler());

    server.setExecutor(null); // Use the default executor
    server.start();
    System.out.println("Server is listening on port " + serverPort);
  }

  static class RootHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      File file = new File("second task/index.html");
      System.out.println("File path: " + file.getAbsolutePath());

      if (file.exists() && file.isFile()) {
        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fis.read(fileData);
        fis.close();

        exchange.sendResponseHeaders(200, file.length());
        OutputStream os = exchange.getResponseBody();
        os.write(fileData);
        os.close();
      } else {
        String response = "File not found";
        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
      }
    }
  }

  static class CSSHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      File file = new File("second task/styles.css");

      if (file.exists() && file.isFile()) {
        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fis.read(fileData);
        fis.close();

        exchange.sendResponseHeaders(200, file.length());
        OutputStream os = exchange.getResponseBody();
        os.write(fileData);
        os.close();
      } else {
        String response = "File not found";
        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
      }
    }
  }

  static class ScriptHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      File file = new File("second task/script.js");

      if (file.exists() && file.isFile()) {
        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fis.read(fileData);
        fis.close();

        exchange.sendResponseHeaders(200, file.length());
        OutputStream os = exchange.getResponseBody();
        os.write(fileData);
        os.close();
      } else {
        String response = "File not found";
        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
      }
    }
  }

  static class StudentsListHandler implements HttpHandler {
    private Gson gson = new Gson();

    public void handle(HttpExchange exchange) throws IOException {
      if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
        List<String> studentList = studentTable.getStudentList(dbName);
        System.out.println("Student list: " + studentList);

        String jsonResponse = gson.toJson(studentList);
        System.out.println("Student list response: " + jsonResponse);

        sendResponse(exchange, jsonResponse);
      }
    }

    // Метод для отправки ответа
    private void sendResponse(HttpExchange exchange, String response) throws IOException {
      byte[] bytes = response.getBytes("UTF-8");
      exchange.sendResponseHeaders(200, bytes.length);
      OutputStream os = exchange.getResponseBody();
      os.write(bytes);
      os.flush();
      os.close();
    }
  }

  static class CreateStudentHandler implements HttpHandler {
    private final JSONParser jsonParser = new JSONParser();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);

        // Чтение JSON-данных
        try {
          JSONObject jsonObject = (JSONObject) jsonParser.parse(new BufferedReader(isr));

          // Получение данных из JSON объекта
          String firstName = (String) jsonObject.get("firstName");
          String lastName = (String) jsonObject.get("lastName");
          String middleName = (String) jsonObject.get("middleName");
          // Парсинг даты рождения, предполагая, что она приходит в нужном формате
          java.sql.Date birthdate = java.sql.Date.valueOf((String) jsonObject.get("birthdate"));
          String groupName = (String) jsonObject.get("group");

          // Ваш код для добавления студента в базу данных
          studentTable.addStudent(dbName, firstName, lastName, middleName, birthdate, groupName);

          String response = "Student created";
          sendResponse(exchange, response);
        } catch (ParseException e) {
          e.printStackTrace();
          String errorResponse = "Invalid JSON format";
          exchange.sendResponseHeaders(400, errorResponse.length());
          exchange.getResponseBody().write(errorResponse.getBytes());
        }
      } else {
        // Обработка других HTTP-методов, если необходимо
        String response = "Method not allowed";
        exchange.sendResponseHeaders(405, response.length());
        exchange.getResponseBody().write(response.getBytes());
      }
      exchange.getResponseBody().close();
    }

    // Метод для отправки ответа
    private void sendResponse(HttpExchange exchange, String response) throws IOException {
      exchange.sendResponseHeaders(200, response.length());
      exchange.getResponseBody().write(response.getBytes());
    }
  }

  static class DeleteStudentHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      // Handle DELETE request for /delete-student
      // Process logic to delete a student
      String response = "Student deleted";
      sendResponse(exchange, response);
    }
  }

  private static void sendResponse(HttpExchange exchange, String response) throws IOException {
    exchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
