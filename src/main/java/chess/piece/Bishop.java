package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.List;

public class Bishop extends Piece{

    @Override
    public boolean canMove(Position start, Position target) {
        return start.onDiagonal(target);
    }
}
