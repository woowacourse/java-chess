package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {
    private static final String name = "K";

    private List<Direction> directions;

    public King() {
        super(name);
        directions = Direction.pullAllBasicDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction gap = from.calculateGap(to);
        for (Direction direction : directions) {
            if (gap == direction) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
