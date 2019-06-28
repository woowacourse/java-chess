package chess.model.unit;

import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(Side side) {
        super(UnitType.KNIGHT, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Direction.valueOfKnight().stream()
                .map(direction -> new SquareNavigator(direction, beginSquare, 1))
                .collect(Collectors.toList());
    }
}