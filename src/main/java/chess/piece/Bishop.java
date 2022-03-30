package chess.piece;

import chess.Direction;
import chess.square.Square;
import java.util.ArrayList;
import java.util.List;

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
        return movableSquares(source).contains(target);
    }

    private List<Square> movableSquares(Square source) {
        List<Square> squares = new ArrayList<>();
//        for (Direction direction : getDirection()) {
//            source.movableSquaresBy(direction);
//        }
        return squares;
    }

    private List<Direction> getDirection() {
        return List.of(Direction.NORTH_WEST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }


}
