package chess.dao;

import chess.dao.util.DatabaseConnector;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String INVALID_PIECE_VALUE_EXCEPTION_MESSAGE = "유효하지 않은 입력입니다.";
    private static final String INVALID_GAME_ID_EXCEPTION_MESSAGE = "유효하지 않은 게임 방 번호입니다.";

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void saveAllByGameId(List<Piece> pieces, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into piece (name, color, position, game_id) values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            for (Piece piece : pieces) {
                statement.setString(1, piece.getName());
                statement.setString(2, piece.getColor().getName());
                statement.setString(3, piece.getPosition().getPosition());
                statement.setString(4, gameId);
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_PIECE_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public Pieces findAllByGameId(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece where game_id = ?";
        final List<Piece> members = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(PieceFactory.of(resultSet.getString("name"), resultSet.getString("color"),
                    resultSet.getString("position")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
        return new Pieces(members);
    }

    public void deleteAllByGameId(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }
}
