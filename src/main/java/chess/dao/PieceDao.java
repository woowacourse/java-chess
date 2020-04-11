package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

public interface PieceDao {
    int countSavedPieces(String gameId);

    void addPiece(String gameId, Position position, Piece piece);

    String findPieceNameByPosition(String gameId, Position position);

    void updatePiece(String gameId, Position position, Piece piece);

    void deleteBoardStatus(String gameId);
}
