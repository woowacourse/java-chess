package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Pawn extends Piece {
    private static final String ERROR_CANT_MOVE = "폰이 이동할 수 없는 위치입니다.";
    private static final String ERROR_CANT_CAPTURE = "폰이 잡을 수 없는 위치입니다.";
    private static final int DISTANCE_NOT_MOVED = 0;
    private static final int DISTANCE_MOVABLE_AT_FIRST_TURN = 2;
    private static final int DISTANCE_MOVABLE = 1;
    private static final double SCORE = 1;

    private boolean firstMove;

    public Pawn(Camp camp) {
        super(camp);
        this.firstMove = true;
    }

    @Override
    public void move(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        if (!canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(ERROR_CANT_MOVE);
        }
        moveApplier.accept(this);
        this.firstMove = false;
    }

    @Override
    public void capture(Position sourcePosition, Position targetPosition, Consumer<Piece> moveApplier) {
        if (!canCapture(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(ERROR_CANT_CAPTURE);
        }
        moveApplier.accept(this);
        this.firstMove = false;
    }

    @Override
    protected boolean canMove(Position sourcePosition, Position targetPosition) {
        int rowDirectedDistance = targetPosition.rowDirectedDistance(sourcePosition);
        int columnDistance = targetPosition.columnDistance(sourcePosition);
        if (columnDistance != DISTANCE_NOT_MOVED) {
            return false;
        }
        if (firstMove) {
            return checkMovableLimitByCamp(rowDirectedDistance, DISTANCE_MOVABLE_AT_FIRST_TURN);
        }
        return checkMovableLimitByCamp(rowDirectedDistance, DISTANCE_MOVABLE);

    }

    private boolean canCapture(Position sourcePosition, Position targetPosition) {
        int columnDistance = targetPosition.columnDistance(sourcePosition);
        int rowDistance = targetPosition.rowDirectedDistance(sourcePosition);
        return columnDistance == DISTANCE_MOVABLE && checkMovableLimitByCamp(rowDistance, DISTANCE_MOVABLE);
    }

    private boolean checkMovableLimitByCamp(int distance, int movableDistance) {
        if (this.isCamp(Camp.BLACK)) {
            return Camp.BLACK.giveVerticalDirectionTo(movableDistance) <= distance && distance < DISTANCE_NOT_MOVED;
        }
        return DISTANCE_NOT_MOVED < distance && distance <= movableDistance;
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
