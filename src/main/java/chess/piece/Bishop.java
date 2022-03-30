package chess.piece;

import chess.Direction;
import chess.square.Square;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "b";
    }

    @Override
    public boolean movable(Square source, Square target) {
        List<List<Square>> lists = movableSquares(source);
        return lists.stream()
                .anyMatch(squares -> squares.contains(target));
    }

    private List<List<Square>> movableSquares(Square source) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, 7))
                .collect(Collectors.toList());
    }

    private List<Direction> getDirection() {
        return List.of(Direction.NORTH_WEST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }
}
