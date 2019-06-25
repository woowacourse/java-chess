package chess.model.unit;

import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {
    Queen(Side side) {
        super(UnitType.QUEEN, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Stream.of(Direction.valueOfDiagonal(), Direction.valueOfOrthogonal())
                .flatMap(Collection::stream)
                .map(direction -> new SquareNavigator(direction, beginSquare, Integer.MAX_VALUE))
                .collect(Collectors.toList());
    }
}