package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

public interface PieceDao {
    int countSavedInfo(String user_id);

    void addPiece(String user_id, Position position, Piece piece);

    String findPieceNameByPosition(String user_id, Position position);

    void updatePiece(String user_id, Position position, Piece piece);

    void deleteSavedInfo(String user_id);
}
