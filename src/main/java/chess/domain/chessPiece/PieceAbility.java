package chess.domain.chessPiece;

import chess.domain.Position;

public interface PieceAbility {
    boolean isMovable(Piece targetPiece, Position targetPosition);

    String pieceName();

    boolean isEqualPosition(Position position);
}
