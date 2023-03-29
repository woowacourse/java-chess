package chess.dao.boardpieces;

import chess.dao.JdbcTemplate;
import chess.domain.Camp;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class LocalBoardPiecesDao implements BoardPiecesDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Optional<Map<Position, Piece>> find(final int boardId) {
        final String sql = "SELECT p.position_file, p.position_rank, p.piece_type, p.piece_camp "
                + "FROM board_pieces as p, board_statuses as s "
                + "WHERE p.board_id = ? "
                + "AND p.board_id = s.board_id "
                + "AND s.is_over = 'N'";

        Map<Position, Piece> result = jdbcTemplate.executeQuery(sql, List.of(boardId), resultSet -> {
            Map<Position, Piece> piecesByPosition = new HashMap<>();
            while (resultSet.next()) {
                int file = resultSet.getInt(1);
                int rank = resultSet.getInt(2);
                PieceType pieceType = PieceType.valueOf(resultSet.getString(3));
                Camp pieceCamp = Camp.valueOf(resultSet.getString(4));
                piecesByPosition.put(Position.of(file, rank), pieceType.createPiece(pieceCamp));
            }
            return piecesByPosition;
        });

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public void insertOrUpdate(final int boardId, final Map<Position, Piece> piecesByPosition) {
        for (Entry<Position, Piece> square : piecesByPosition.entrySet()) {
            insertOrUpdatePiece(boardId, square.getKey(), square.getValue());
        }
    }

    private void insertOrUpdatePiece(final int boardId, final Position position, final Piece piece) {
        final String sql = "INSERT INTO board_pieces "
                + "(board_id, position_file, position_rank, piece_type, piece_camp) "
                + "VALUES (?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "position_file = ?, position_rank = ?, piece_type = ?, piece_camp = ?";

        int file = position.getFile();
        int rank = position.getRank();
        String pieceTypeName = piece.getTypeName();
        String pieceCampName = piece.getCamp().name();

        jdbcTemplate.executeUpdate(sql, List.of(
                boardId, file, rank, pieceTypeName, pieceCampName,
                file, rank, pieceTypeName, pieceCampName
        ));
    }

    @Override
    public void delete(int boardId) {
        final String sql = "DELETE FROM board_pieces WHERE board_id = ?";

        jdbcTemplate.executeUpdate(sql, List.of(boardId));
    }
}
