package chess.dao;

import chess.domain.game.ChessGameEntity;
import chess.exception.NotFoundChessGameException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDAO {

    private final ConnectionFactory factory;

    public ChessGameDAO() {
        factory = new ConnectionFactory();
    }

    public ChessGameEntity findLatestOne() throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM chess_games ORDER BY id DESC LIMIT 1";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new NotFoundChessGameException();
            }

            return new ChessGameEntity(rs.getLong("id"), rs.getString("state"));
        }
    }

    public void create() throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO chess_games(state) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "Ready");
            preparedStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM chess_games WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
