import java.sql.*;

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
      DatabaseMetaData metaData = connection.getMetaData();

      ResultSet resultSet = metaData.getCatalogs();
      boolean dbExists = false;

      while (resultSet.next()) {
        String databaseName = resultSet.getString("TABLE_CAT");
        if (databaseName.equals(dbName)) {
          dbExists = true;
          break;
        }
      }

      resultSet.close();

      if (dbExists) {
        System.out.println("Database already exists.");
      } else {
        Statement statement = connection.createStatement();
        String createDBSQL = "CREATE DATABASE " + dbName;
        statement.executeUpdate(createDBSQL);
        System.out.println("Database created successfully.");
        statement.close();
      }

      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
