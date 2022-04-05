package chess.db.dao;

import static chess.util.DatabaseUtil.parameterGroupOf;
import static chess.util.DatabaseUtil.parameterGroupsOf;

import chess.db.entity.PieceEntity;
import chess.domain.board.position.Position;
import chess.util.DatabaseUtil;
import java.sql.Connection;
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
        final String sql = String.format("INSERT INTO %s (game_id, position, type, color) VALUES %s",
                table, parameterGroupsOf(pieces, 4));

        CommandBuilder command = new CommandBuilder(sql);
        for (PieceEntity piece : pieces) {
            command.setInt(gameId)
                    .setString(piece.getPositionKey())
                    .setString(piece.getType())
                    .setString(piece.getColor());
        }
        command.execute();
    }

    public void deleteAllByGameIdAndPositions(int gameId, List<Position> positions) {
        final String sql = String.format("DELETE FROM %s WHERE game_id = ? AND position IN %s",
                table, parameterGroupOf(positions.size()));

        CommandBuilder command = new CommandBuilder(sql).setInt(gameId);
        for (Position position : positions) {
            command.setString(position.toKey());
        }
        command.execute();
    }
}
