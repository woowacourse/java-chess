package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.List;

public class Pawn extends Piece {

    private static final String name = "p";
    private static final String MOVE_FORWARD_ERROR_GUIDE_MESSAGE = "앞에 기물이 있어 Pawn은 앞으로 이동할 수 없습니다";
    private static final String MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE = "Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다";
    private static int movableDistance = 2;
    private final List<Direction> movableDirection;
    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(name, color);
        this.movableDirection = createMovableDirectionByColor(color);
    }

    private List<Direction> createMovableDirectionByColor(Color color) {
        if (color == Color.BLACK) {
            return List.of(Direction.BOTTOM, Direction.BOTTOM_LEFT,
                    Direction.BOTTOM_RIGHT);
        }
        return List.of(Direction.TOP, Direction.TOP_LEFT,
                Direction.TOP_RIGHT);
    }

    @Override
    public boolean isMovableAtOnce(int absGapOfColum, int absGapOfRank) {
        return absGapOfColum <= movableDistance && absGapOfRank <= movableDistance;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return movableDirection.contains(direction);
    }

    public void setFirstMove(boolean firstMove) {
        this.isFirstMove = firstMove;
        this.movableDistance = 1;
    }

    public void checkMoveForwardByPawn(Direction direction, boolean isPositionToMoveEmpty) {
        if (isForwardDirection(direction) && !isPositionToMoveEmpty) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_GUIDE_MESSAGE);

        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    public void checkMoveDiagonalByPawn(Direction direction, boolean isPositionToMoveEmpty) {
        if (isDiagonalDirection(direction) && isPositionToMoveEmpty) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE);
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }
}
