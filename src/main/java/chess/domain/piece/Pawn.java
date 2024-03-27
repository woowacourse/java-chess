package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Map;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        if (target.equals(source.forward(color()))) {
            return board.get(target).doesNotExist();
        }
        if (target.equals(source.forward(color()).forward(color()))) {
            return board.get(source.forward(color())).doesNotExist() && board.get(target).doesNotExist();
        }
        if (target.equals(source.forward(color()).left()) || target.equals(source.forward(color()).right())) {
            return board.get(target).hasOppositeColorFrom(this);
        }
        return false;
    }

    @Override
    public boolean exists() {
        return true;
    }
}
