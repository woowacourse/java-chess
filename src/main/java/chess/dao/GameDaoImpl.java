package chess.dao;

import chess.domain.piece.PieceColor;
import chess.dto.TurnDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {

    public TurnDto getTurn() {
        String sql = "SELECT game.turn FROM game WHERE id = '1'";
        String turn = "";
        try (Connection connection = JdbcTemplateUtil.getConnection();
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

    public void changeTurn() {
        String changeTurnSql = "UPDATE game SET turn = ? WHERE id = ?";

        try (Connection connection = JdbcTemplateUtil.getConnection();
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
