import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTableHandler {
  private String jdbcUrl;
  private String username;
  private String password;

  public StudentTableHandler(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public void createStudentTable(String dbName) {
    try (Connection connection = DriverManager.getConnection(jdbcUrl + dbName, username, password);
        Statement statement = connection.createStatement()) {
      String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
          "id SERIAL PRIMARY KEY," +
          "first_name VARCHAR(50) NOT NULL," +
          "last_name VARCHAR(50) NOT NULL," +
          "middle_name VARCHAR(50)," +
          "date_of_birth DATE NOT NULL," +
          "group_name VARCHAR(20) NOT NULL" +
          ")";
      statement.executeUpdate(createTableSQL);
      System.out.println("Table created successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addStudent(String dbName, String firstName, String lastName, String middleName,
      Date dob, String groupName) {
    try (Connection connection = DriverManager.getConnection(jdbcUrl + dbName, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO students (first_name, last_name, middle_name, date_of_birth, group_name) VALUES (?, ?, ?, ?, ?)")) {
      preparedStatement.setString(1, firstName);
      preparedStatement.setString(2, lastName);
      preparedStatement.setString(3, middleName);
      preparedStatement.setDate(4, dob);
      preparedStatement.setString(5, groupName);
      preparedStatement.executeUpdate();
      System.out.println("Student added successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<String> getStudentList(String dbName) {
    List<String> studentList = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(jdbcUrl + dbName, username, password);
        Statement statement = connection.createStatement()) {
      String selectQuery = "SELECT * FROM students";
      ResultSet resultSet = statement.executeQuery(selectQuery);
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String middleName = resultSet.getString("middle_name");
        Date dob = resultSet.getDate("date_of_birth");
        String groupName = resultSet.getString("group_name");
        String studentInfo = "ID: " + id + ",\nИмя: " + firstName + ",\nФамилия: " + lastName +
            ",\nОтчество: " + middleName + ",\nДата рождения: " + dob +
            ",\nГруппа: " + groupName;
        studentList.add(studentInfo);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return studentList;
  }

  public void deleteStudentByUniqueNumber(String dbName, Integer uniqueNumber) {
    try (Connection connection = DriverManager.getConnection(jdbcUrl + dbName, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(
            "DELETE FROM students WHERE id = ?")) {
      preparedStatement.setInt(1, uniqueNumber);
      int deletedRows = preparedStatement.executeUpdate();
      if (deletedRows > 0) {
        System.out.println("Student with id " + uniqueNumber + " deleted successfully.");
      } else {
        System.out.println("No student found with id " + uniqueNumber);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
