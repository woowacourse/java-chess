package chess.model.unit;

import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    public Rook(Side side) {
        super(UnitType.ROOK, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Direction.valueOfOrthogonal().stream()
                .map(direction -> new SquareNavigator(direction, beginSquare, Integer.MAX_VALUE))
                .collect(Collectors.toList());
    }
}