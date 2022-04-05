package chess.dao;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface PieceDao {

    Map<Position, Piece> findAllPieces();

    void savePieces(Map<Position, Piece> chessBoard);

    void updatePiecePosition(Position position, Position movePosition);

    void updatePiece(Position position, Piece changedPiece);

    void deletePiece(Position position);

    void deleteAllPiece();
}
