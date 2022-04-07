package chess.dao;

import chess.dto.PieceDto;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DbPieceDao implements PieceDao {

    @Override
    public boolean isExist() {
        final String sql = "select * from piece";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<Position, Piece> load() {
        final String sql = "select * from piece";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            return getBoard(statement);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    private Map<Position, Piece> getBoard(final PreparedStatement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery();
        final Map<Position, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            board.put(getPosition(resultSet), getPiece(resultSet));
        }
        return board;
    }

    @Override
    public void save(final int boardId, final PieceDto pieceDto) {
        final String sql = "insert into piece (board_id, name, position, color) values (?, ?, ?, ?)";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            statement.setString(2, pieceDto.getName());
            statement.setString(3, pieceDto.getPosition());
            statement.setString(4, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(final int boardId, final Map<Position, Piece> board) {
        final String sql = "insert into piece (board_id, name, position, color) values (?, ?, ?, ?)";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
                PieceDto pieceDto = PieceDto.toDto(entry.getValue(), entry.getKey());
                statement.setInt(1, boardId);
                statement.setString(2, pieceDto.getName());
                statement.setString(3, pieceDto.getPosition());
                statement.setString(4, pieceDto.getColor());
                statement.executeUpdate();
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Position, Piece> findAllByBoardId(final int boardId) {
        final String sql = "select * from piece where board_id = (?)";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            return getBoard(statement);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    @Override
    public void deleteAllById(final int boardId) {
        final String sql = "delete from piece where board_id = (?)";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private Piece getPiece(final ResultSet resultSet) throws SQLException {
        return Pieces.valueOf(
                resultSet.getString("name")
        );
    }

    private Position getPosition(final ResultSet resultSet) throws SQLException {
        return Position.of(resultSet.getString("position"));
    }
}
