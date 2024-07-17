package org.example;


import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketDao {
    private static final String ID_COLUMN_NAME = "id";
    private static final String USER_ID_COLUMN_NAME = "user_id";
    private static final String TICKET_TYPE_COLUMN_NAME = "ticket_type";
    private static final String CREATION_DATE_COLUMN_NAME = "creation_date";
    private static final String TABLE_NAME = "tickets";

    private static final String SELECT_FROM_TICKETS_BY_ID_SCRIPT =
            "SELECT " + ID_COLUMN_NAME + ", "
                    + USER_ID_COLUMN_NAME + ", "
                    + TICKET_TYPE_COLUMN_NAME + ", "
                    + CREATION_DATE_COLUMN_NAME
                    + " FROM " + TABLE_NAME + " WHERE "
                    + ID_COLUMN_NAME + " = ?";

    private static final String SELECT_FROM_TICKETS_BY_USER_ID_SCRIPT =
            "SELECT " + ID_COLUMN_NAME + ", "
                    + USER_ID_COLUMN_NAME + ", "
                    + TICKET_TYPE_COLUMN_NAME + ", "
                    + CREATION_DATE_COLUMN_NAME
                    + " FROM " + TABLE_NAME + " WHERE "
                    + USER_ID_COLUMN_NAME + " = ?";

    private static final String UPDATE_TICKETS_SCRIPT =
            "UPDATE " + TABLE_NAME + " SET "
                    + TICKET_TYPE_COLUMN_NAME + " = ?::"
                    + TICKET_TYPE_COLUMN_NAME + " WHERE "
                    + ID_COLUMN_NAME + " = ?";

    private static final String INSERT_INTO_TICKETS_SCRIPT =
            "INSERT INTO " + TABLE_NAME + " ("
                    + USER_ID_COLUMN_NAME + ", "
                    + TICKET_TYPE_COLUMN_NAME
                    + ") VALUES (?,?::"
                    + TICKET_TYPE_COLUMN_NAME + ")";

    private final DataSource dataSource;

    public TicketDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createTicket(int userId, TicketType type) throws SQLException {

        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TICKETS_SCRIPT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, userId);
                statement.setString(2, type.toString());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating ticket failed, no rows affected.");
                }

                int ticketId;
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ticketId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating ticket failed, no ID obtained.");
                    }
                }

                connection.commit();
                return ticketId;
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

    public Optional<Ticket> getTicketById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_TICKETS_BY_ID_SCRIPT)) {

            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int userId = result.getInt(USER_ID_COLUMN_NAME);
                    TicketType type = TicketType.valueOf(result.getString(TICKET_TYPE_COLUMN_NAME));
                    Timestamp creationDate = result.getTimestamp(CREATION_DATE_COLUMN_NAME);
                    return Optional.of(new Ticket(id, userId, type, creationDate));
                }
            }

            return Optional.empty();
        }
    }

    public List<Ticket> getTicketsByUserId(int userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_TICKETS_BY_USER_ID_SCRIPT)) {
            statement.setInt(1, userId);
            try (ResultSet result = statement.executeQuery()) {
                ArrayList<Ticket> tickets = new ArrayList<>();
                while (result.next()) {
                    int id = result.getInt(ID_COLUMN_NAME);
                    TicketType type = TicketType.valueOf(result.getString(TICKET_TYPE_COLUMN_NAME));
                    Timestamp creationDate = result.getTimestamp(CREATION_DATE_COLUMN_NAME);
                    tickets.add(new Ticket(id, userId, type, creationDate));
                }
                return tickets;
            }
        }
    }

    public void updateTicketType(int id, TicketType newType) throws SQLException {

        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(UPDATE_TICKETS_SCRIPT)) {
                connection.setAutoCommit(false);

                statement.setString(1, newType.toString());
                statement.setInt(2, id);
                statement.executeUpdate();

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
