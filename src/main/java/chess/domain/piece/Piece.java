package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Map;

public interface Piece {
    Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition);

    void validateMovement(Position currentPosition, Position nextPosition);

    boolean isOpponent(Color other);

    boolean isOpponent(Piece other);

    boolean isSameColor(Color other);

    boolean isSameColor(Piece other);

    String formatName();

    boolean isEmpty();

    boolean isPiece();

    boolean isKing();

    boolean isSliding();

    void addPieceType(Map<PieceType, Integer> pieceCounter);

    String getPieceTypeName();

    String getColorName();
}
