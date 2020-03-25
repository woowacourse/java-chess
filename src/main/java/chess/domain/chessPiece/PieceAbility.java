package chess.domain.chessPiece;

import chess.domain.Position;

public interface PieceAbility {
    boolean isMovable(Position target, Piece targetPiece);

    String pieceName();

    boolean isEqualPosition(Position position);
}
