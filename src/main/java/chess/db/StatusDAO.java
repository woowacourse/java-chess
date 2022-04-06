package chess.db;

import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusDAO extends CommonDAO {

    private static final String FIND_COLOR_SQL = "select color from state where roomID = ?";
    private static final String CONVERT_COLOR_SQL = "update state set color = ? where roomID = ?";
    private static final String CHECK_SAVE_SQL = "select id from room where id = ?";
    private static final String TERMINATE_GAME_SQL = "delete from room where id = ?";
    private static final String INITIALIZE_ID_SQL = "insert into room values (?)";
    private static final String INITIALIZE_COLOR_SQL = "insert into state values (?, ?)";

    public StatusDAO(String roomId) {
        super(roomId);
    }

    public Color findColor() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_COLOR_SQL);
            setStrings(statement, List.of(roomId));
            return getColor(statement);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private Color getColor(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Color.valueOf(resultSet.getString("color"));
        }
        return null;
    }

    public void convertColor(Color color) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CONVERT_COLOR_SQL);
            setStrings(statement, List.of(color.name(), roomId));
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isSaved() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CHECK_SAVE_SQL);
            setStrings(statement, List.of(roomId));
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException | NullPointerException exception) {
            return false;
        }
    }

    public void terminateDB() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(TERMINATE_GAME_SQL);
            setStrings(statement, List.of(roomId));
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void initializeID() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INITIALIZE_ID_SQL);
            setStrings(statement, List.of(roomId));
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void initializeColor() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INITIALIZE_COLOR_SQL);
            setStrings(statement, List.of(roomId, Color.WHITE.name()));
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
