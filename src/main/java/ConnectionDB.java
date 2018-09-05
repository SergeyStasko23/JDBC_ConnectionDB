import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionDB {
    public void connectDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение данных из config.properties для подключения к БД.
        Properties property = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ERROR: config.properties is not exist!");
        }

        final String host = property.getProperty("db.host");
        final String login = property.getProperty("db.login");
        final String password = property.getProperty("db.password");

        try(Connection connection = DriverManager.getConnection(host, login, password)) {
            System.out.println("Connection successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection unsuccessfully");
        }
    }
}
