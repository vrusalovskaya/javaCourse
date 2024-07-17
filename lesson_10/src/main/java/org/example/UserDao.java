package org.example;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
public class UserDao {

    private static final String USERS_TABLE_NAME = "users";
    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String CREATION_DATE_COLUMN_NAME = "creation_date";

    private static final String INSERT_INTO_USERS_SCRIPT =
            "INSERT INTO " + USERS_TABLE_NAME + " ("
                    + NAME_COLUMN_NAME + ") VALUES (?)";

    private static final String SELECT_FROM_USERS_SCRIPT =
            "SELECT " + ID_COLUMN_NAME + ", "
                    + NAME_COLUMN_NAME + ", "
                    + CREATION_DATE_COLUMN_NAME
                    + " FROM " + USERS_TABLE_NAME + " WHERE "
                    + ID_COLUMN_NAME + " = ?";

    private static final String DELETE_FROM_USERS_SCRIPT =
            "DELETE FROM " + USERS_TABLE_NAME + " WHERE "
                    + ID_COLUMN_NAME + " = ?";


    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createUser(String name) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USERS_SCRIPT, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                int userId;
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                connection.commit();
                return userId;
            }

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }

            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Optional<User> getUserById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS_SCRIPT)
        ) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString(NAME_COLUMN_NAME);
                    Timestamp creationDate = result.getTimestamp(CREATION_DATE_COLUMN_NAME);
                    return Optional.of(new User(id, name, creationDate));
                }
            }
            return Optional.empty();
        }
    }

    public void deleteUserById(int id) throws SQLException {

        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(DELETE_FROM_USERS_SCRIPT)) {

                statement.setInt(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting user failed, no rows affected.");
                }

                connection.commit();
            }

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }

            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
