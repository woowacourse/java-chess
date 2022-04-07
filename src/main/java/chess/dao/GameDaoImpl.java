package chess.dao;

import chess.domain.piece.PieceColor;
import chess.dto.TurnDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public TurnDto getTurn() {
        String sql = "SELECT game.turn FROM game WHERE id = '1'";
        String turn = "";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            validateTurnExist(resultSet);
            turn = resultSet.getString("turn");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TurnDto(turn);
    }

    private void validateTurnExist(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalStateException();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void changeTurn() {
        String changeTurnSql = "UPDATE game SET turn = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(changeTurnSql)) {
            TurnDto currentTurn = getTurn();
            statement.setString(1, PieceColor.getOpposite(currentTurn.getTurn()));
            statement.setString(2, "1");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
