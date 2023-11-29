import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {

  public static void main(String[] args) throws IOException {
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
    public void handle(HttpExchange exchange) throws IOException {
      // Handle GET request for /students-list
      // Process logic to get student list
      String response = "Student List";
      sendResponse(exchange, response);
    }
  }

  static class CreateStudentHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      // Handle POST request for /create-student
      // Process logic to create a student
      String response = "Student created";
      sendResponse(exchange, response);
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
