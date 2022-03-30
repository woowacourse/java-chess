package chess.model.piece;

import chess.Direction;
import chess.model.square.Square;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "r";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }

    @Override
    List<Direction> getDirection() {
        return null;
    }
}
