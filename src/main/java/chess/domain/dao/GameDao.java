package chess.domain.dao;

import chess.domain.dto.GameDto;
import chess.domain.game.board.ChessBoard;

import java.sql.*;

public class GameDao {

    private final Connection connection;
    private int id = 0;

    public GameDao() {
        connection = Connector.getConnection();
    }

    public GameDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(ChessBoard chessBoard) {
        final String sql = "insert into Game (id, status, turn) values(?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, ++id);
            statement.setBoolean(2, chessBoard.isPlaying());
            statement.setString(3, chessBoard.getCurrentTurn().name());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public GameDto findLastGame() throws SQLException {
        final String sql = "SELECT * FROM Game ORDER BY id DESC LIMIT 1";
        final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            return null;
        }
        return new GameDto(
                result.getInt("id"),
                result.getBoolean("status"),
                result.getString("turn")
        );
    }

    public GameDto findById(int id) {
        final String sql = "select id, status, turn from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            final ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new GameDto(
                    result.getInt("id"),
                    result.getBoolean("status"),
                    result.getString("turn")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void delete(){
        final String sql = "delete from game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,findLastGame().getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
