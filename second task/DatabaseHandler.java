import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
  private String jdbcUrl;
  private String username;
  private String password;

  public DatabaseHandler(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public void createDatabase(String dbName) {
    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      Statement statement = connection.createStatement();

      String createDBSQL = "CREATE DATABASE " + dbName;
      statement.executeUpdate(createDBSQL);
      System.out.println("Database created successfully.");

      statement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public StudentTableHandler getStudentTableHandler(String dbName) {
    return new StudentTableHandler(jdbcUrl, username, password);
  }
}
