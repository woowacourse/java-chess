package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.List;

import static chess.domain.score.Score.ROOK_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;


public class Rook extends Piece {

    private static final List<Direction> direction = List.of(Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT);

    public Rook(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), ROOK_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkDirection(Direction direction) {
        if (!Rook.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }
}
