package chess.piece;

import chess.Movement;
import chess.Position;
import java.util.List;

public class Queen extends Piece{

    @Override
    public boolean canMove(Position start, Position target) {
        return start.onStraight(target) || start.onDiagonal(target);
    }

}
