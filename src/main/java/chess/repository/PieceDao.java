package chess.repository;

import chess.domain.ChessBoard;
import chess.domain.InitialPiece;
import chess.domain.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {

    private final JdbcTemplate jdbcTemplate;

    public PieceDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final Map<Position, Piece> piecesByPosition, final long game_id) {
        piecesByPosition.forEach((position, piece) -> saveEachPiece(piece, position, game_id));
    }

    private void saveEachPiece(final Piece piece, final Position position, final long gameId) {
        String queryStatement = "INSERT INTO piece (type, position, color, game_id) VALUES(?, ?, ?, ?)";

        jdbcTemplate.save(queryStatement, piece.getType().name(), position.toString(),
            piece.getColor().name(), gameId);
    }

    public void updatePositionByPositionAndGameId(final Position prev,
        final long gameId,
        final Position current) {
        String queryStatement = "UPDATE piece SET position = ? WHERE game_id = ? AND position = ?";

        jdbcTemplate.updateOne(queryStatement, current.toString(), gameId, prev.toString());
    }

    public void deleteByPositionAndGameId(final Position target, final long gameId) {
        String queryStatement = "DELETE FROM piece WHERE game_id = ? AND position = ?";

        jdbcTemplate.deleteOne(queryStatement, gameId, target.toString());
    }

    public ChessBoard findAllByGameId(final long gameId) {
        String queryStatement = "SELECT * FROM piece WHERE game_id = ?";

        return jdbcTemplate.query(queryStatement, gameId, (resultSet) -> {
            Map<Position, Piece> piecesByPosition = new HashMap<>();
            while (resultSet.next()) {
                piecesByPosition.put(Position.from(resultSet.getString("position")),
                    InitialPiece.findPieceByTypeAndColor(
                        PieceType.findByName(resultSet.getString("type")),
                        TeamColor.findByName(resultSet.getString("color"))));
            }
            return new ChessBoard(piecesByPosition);
        });
    }

}
