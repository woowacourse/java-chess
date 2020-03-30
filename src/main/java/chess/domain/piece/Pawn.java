package chess.domain.piece;

import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public class Pawn extends Piece {

    private static final int BLACK_FIRST_MOVE_POSITION_Y = 6;
    private static final int WHITE_FIRST_MOVE_POSITION_Y = 1;

    public Pawn(Position position, Color color) {
        super(position, color, Symbol.PAWN);
    }

    private boolean isFirstMove() {
        if (isBlack() && getPosition().equalsY(BLACK_FIRST_MOVE_POSITION_Y)) {
            return true;
        }
        return !isBlack() && getPosition().equalsY(WHITE_FIRST_MOVE_POSITION_Y);
    }

    @Override

    protected List<Direction> movableDirections(Piece piece, Direction direction) {
        if (piece instanceof Blank && direction != Direction.NORTH && direction != Direction.SOUTH) {
            throw new NotMovableException();
        }
        if (!(piece instanceof Blank) && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
            throw new NotMovableException();
        }
        return isBlack() ? Direction.BLACK_PAWN_DIRECTION : Direction.WHITE_PAWN_DIRECTION;
    }

    @Override
    protected Direction findDirection(int x, int y) {
        if (isFirstMove()) {
            return Direction.ofStart(x, y);
        }
        return Direction.of(x, y);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
