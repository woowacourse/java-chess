package chess.domain.piece;

import chess.domain.Position;

public interface ChessPiece {

    void move();
    String name();
    boolean canMove(Position origin, Position destination);
}
