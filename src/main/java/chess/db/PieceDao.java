package chess.db;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.List;

public interface PieceDao {
    int countSavedPieces(String gameId);

    void addInitialPieces(List<ChessPiece> pieces);

    void addPiece(ChessPiece piece);

    String findPieceNameByPosition(String gameId, Position position);

    void updatePiece(String gameId, Position position, Piece piece);

    void deleteBoardStatus(String gameId);
}
