package cz.skillmeaacademy.db;

import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DBConntactService {
    private static final String READ_ALL = "SELECT * FROM contact";
    private static final String CREATE = "INSERT INTO contact (name, email, phone) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM contact WHERE id =?";
    private static final Logger logger = getLogger(DBConntactService.class);

    public List<Contact> readAll() {
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL)) {

            ResultSet resultSet = statement.executeQuery();
            List<Contact> contacts = new ArrayList<>();
            while (resultSet.next()) {
                contacts.add(new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                ));
            }
            return contacts;
        } catch (SQLException e) {
            logger.error("Error while reading all contacts!", e);
            return null;
        }
    }

    public int create(String name, String email, String phone) {
        try (
                Connection conn = HikariCPDataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement(CREATE))
        {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);

            return statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Contact with this email or phone already exists");
            return 0;
        } catch (SQLException e) {
            logger.error("Error while creating new contact", e);
            return 0;

        }
    }
    public int delete(int id){
        try (
                Connection conn = HikariCPDataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE)){

            statement.setInt(1, id);
            //returns number of affected rows
            return statement.executeUpdate();
        }catch (SQLException e){
            logger.error("Error while deleting contact!", e);
            return 0;
        }

    }

}
