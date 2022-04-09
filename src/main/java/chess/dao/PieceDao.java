package chess.dao;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.util.Map;

public interface PieceDao {

    void saveAllPieces(final Map<Position, Piece> board);

    Map<String, PieceDto> findAllPieces();

    void removePieceByPosition(final String position);

    void updatePiece(final String position, final Piece piece);

    void removeAllPieces();
}
