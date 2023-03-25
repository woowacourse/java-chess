package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.PieceEntity;

import java.util.List;

public class PieceDao {

    private final JdbcTemplate template;

    public PieceDao(final JdbcTemplate template) {
        this.template = template;
    }

    public void saveAll(final List<PieceEntity> pieceEntities) {
        final String sql =
                "INSERT INTO piece(pos_rank, pos_file, color, type, chess_game_id) VALUES (?, ?, ?, ?, ?)";

        template.execute(sql, ((connection, preparedStatement) -> {
            for (final PieceEntity pieceEntity : pieceEntities) {
                preparedStatement.setString(1, String.valueOf(pieceEntity.rank()));
                preparedStatement.setString(2, String.valueOf(pieceEntity.file()));
                preparedStatement.setString(3, pieceEntity.color());
                preparedStatement.setString(4, pieceEntity.movementType());
                preparedStatement.setLong(5, pieceEntity.chessGameId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }));
    }

    public List<PieceEntity> findByAllChessGameId(final Long chessGameId) {
        final String query = "SELECT * FROM piece where chess_game_id = ?";
        return template.findAll(query, resultSet -> new PieceEntity(
                resultSet.getInt(2),
                resultSet.getString(3).charAt(0),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getLong(6)
        ), chessGameId.toString());
    }

    public void deleteAllByChessGameId(final Long chessGameId) {
        final String sql = "DELETE FROM piece where chess_game_id = ?";
        template.executeUpdate(sql, chessGameId.toString());
    }

    public void deleteAll() {
        final String sql = "DELETE FROM piece";
        template.executeUpdate(sql);
    }
}
