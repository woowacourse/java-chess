package chess.domain.chessPiece;

import chess.domain.Position;

public interface PieceAbility {
    boolean isMovable();

    String pieceName();

    boolean isEqualPosition(Position position);
}
