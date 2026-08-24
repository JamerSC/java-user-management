package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CREATE USER
    public void save(User user) {

        String sql =
                "INSERT INTO users (name, email) VALUES (?,?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();

            System.out.println("User saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FIND ALL USERS
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql =
                "SELECT id, name, email FROM users";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User user = new User();

                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // FIND USER BY ID
    public User findById(int id) {

        String sql =
                "SELECT id, name, email FROM users WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE USER
    public void update(User user) {

        String sql =
                "UPDATE users SET name = ?, email = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());

            statement.executeUpdate();

            System.out.println("User updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE USER
    public void delete(int id) {

        String sql =
                "DELETE FROM users WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
            )
        {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("User deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
