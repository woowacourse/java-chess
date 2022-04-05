package chess.db.dao;

import static chess.util.DatabaseUtil.parameterGroupOf;
import static chess.util.DatabaseUtil.parameterGroupsOf;

import chess.db.entity.PieceEntity;
import chess.domain.board.position.Position;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final String table;

    public PieceDao(String table) {
        this.table = table;
    }

    public List<PieceEntity> findAllByGameId(int gameId) {
        final String sql = String.format("SELECT position, type, color FROM %s WHERE game_id = ?", table);
        final QueryReader reader = new QueryBuilder(sql).setInt(gameId)
                .execute();

        try (reader) {
            return initPieceEntities(reader);
        }
    }

    private List<PieceEntity> initPieceEntities(QueryReader reader) {
        List<PieceEntity> pieces = new ArrayList<>();
        while (reader.nextRow()) {
            pieces.add(new PieceEntity(
                    reader.readStringAt("position"),
                    reader.readStringAt("type"),
                    reader.readStringAt("color")
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
