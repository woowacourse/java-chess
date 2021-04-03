package chess.dao;

import chess.domain.game.ChessGameEntity;

import java.sql.*;
import java.util.Optional;

public class ChessGameDAO {

    private final ConnectionFactory factory;

    public ChessGameDAO() {
        factory = new ConnectionFactory();
    }

    public Optional<ChessGameEntity> findLatestOne() throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM chess_games ORDER BY id DESC LIMIT 1";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(new ChessGameEntity(rs.getLong("id"), rs.getString("state")));
        }
    }

    public Long create() throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO chess_games(state) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "BlackTurn");
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(Statement.RETURN_GENERATED_KEYS);
            }
        }

        throw new IllegalArgumentException("ChessGame이 제대로 생성되지 않았습니다");
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM chess_games WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void updateState(final Long id, final String state) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "UPDATE chess_games SET state = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, state);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

}
