package chess.domain.chessPiece;

import chess.domain.Position;

public interface Piece {
    boolean isMovable();

    String pieceName();

    boolean isEqualPosition(Position position);
}
