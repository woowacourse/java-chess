package domain.piece;

import domain.position.Position;

import java.util.List;

public interface Piece {
    boolean canMove(Position from, Position to);
}
