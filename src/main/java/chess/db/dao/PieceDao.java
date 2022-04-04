package chess.db.dao;

import chess.db.entity.PieceEntity;
import chess.domain.board.position.Position;
import chess.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final String table;

    public PieceDao(String table) {
        this.table = table;
    }

    public List<PieceEntity> findAllByGameId(int gameId) {
        final String sql = "SELECT position, type, color FROM " + table
                + " WHERE game_id = " + gameId;
        try (final Connection connection = DatabaseUtil.getConnection()) {
            return toEntities(DatabaseUtil.getQueryResult(sql, connection));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 조회 작업에 실패하였습니다.");
        }
    }

    private List<PieceEntity> toEntities(ResultSet resultSet) throws SQLException {
        List<PieceEntity> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(new PieceEntity(
                    resultSet.getString("position"),
                    resultSet.getString("type"),
                    resultSet.getString("color")
            ));
        }
        return pieces;
    }

    public void saveAll(int gameId, List<PieceEntity> pieces) {
        try (final Connection connection = DatabaseUtil.getConnection()) {
            for (PieceEntity piece : pieces) {
                save(gameId, piece, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 저장 작업에 실패하였습니다.");
        }
    }

    private void save(int gameId, PieceEntity piece, Connection connection) throws SQLException {
        final String sql = "INSERT INTO " + table +
                " (game_id, position, type, color) VALUES (" + gameId + ", ?, ?, ?)";
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, piece.getPositionKey());
        statement.setString(2, piece.getType() + "");
        statement.setString(3, piece.getColor() + "");
        statement.executeUpdate();
    }

    public void deleteAllByGameIdAndPositions(int gameId, List<Position> positions) {
        try (final Connection connection = DatabaseUtil.getConnection()) {
            for (Position position : positions) {
                deleteByGameIdAndPosition(gameId, position, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 저장 작업에 실패하였습니다.");
        }
    }

    private void deleteByGameIdAndPosition(int gameId, Position position, Connection connection) throws SQLException {
        final String sql = "DELETE FROM " + table
                + " WHERE game_id = (?) AND position = (?)";
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, gameId);
        statement.setString(2, position.toKey());
        statement.executeUpdate();
    }
}
