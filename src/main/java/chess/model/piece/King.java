package chess.model.piece;

import chess.Direction;
import chess.model.square.Square;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "k";
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
