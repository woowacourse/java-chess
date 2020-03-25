package chess.domain.chessPiece;

import chess.domain.Position;

public interface PieceAbility {
    boolean isMovable(Position source, Position target);

    String pieceName();

    boolean isEqualPosition(Position position);
}
