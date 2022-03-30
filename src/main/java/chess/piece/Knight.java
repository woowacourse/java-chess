package chess.piece;

import chess.Direction;
import chess.square.Square;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "n";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    public List<Direction> getDirection() {
        return List.of(
                Direction.NNE,
                Direction.NNW,
                Direction.SSE,
                Direction.SSW,
                Direction.EEN,
                Direction.EES,
                Direction.WWN,
                Direction.WWS);
    }
}
