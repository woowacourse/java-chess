package chess.piece;

import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.List;

public class Rook extends Piece {

    @Override
    public boolean canMove(Position start, Position target) {
        if (!start.onStraight(target)) {
            return false;
        }

        if (start.onSameRow(target)) {
            int step = target.row().intValue() - start.row().intValue();
            return start.canMoveUp(step);
        }

        if (start.onSameColumn(target)) {
            int step = target.column().intValue() - start.column().intValue();
            return start.canMoveRight(step);
        }
        return false;
    }

}
