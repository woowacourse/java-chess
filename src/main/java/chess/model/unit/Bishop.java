package chess.model.unit;

import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    Bishop(Side side) {
        super(UnitType.BISHOP, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Direction.valueOfDiagonal().stream()
                .map(direction -> new SquareNavigator(direction, beginSquare, Integer.MAX_VALUE))
                .collect(Collectors.toList());
    }
}