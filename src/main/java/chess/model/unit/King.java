package chess.model.unit;

import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    public King(Side side) {
        super(UnitType.KING, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Stream.of(Direction.valueOfDiagonal(), Direction.valueOfOrthogonal())
                .flatMap(Collection::stream)
                .map(direction -> new SquareNavigator(direction, beginSquare, 1))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isKing() {
        return true;
    }
}