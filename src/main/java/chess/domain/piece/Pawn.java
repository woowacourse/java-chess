package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Pawn extends Piece {

    private static final int MOVABLE_DISTANCE_AT_FIRST_TURN = 2;
    private static final int MOVABLE_DISTANCE_AFTER_FIRST_TURN = 1;
    private boolean firstMove;

    public Pawn(Camp camp) {
        super(camp);
        this.firstMove = true;
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        moveFunction.accept(this);
        this.firstMove = false;
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int distance = beforePosition.rowDirectedDistance(afterPosition);
        if (firstMove) {
            return distance == movableDistanceByCamp(MOVABLE_DISTANCE_AT_FIRST_TURN);
        }
        return distance == movableDistanceByCamp(MOVABLE_DISTANCE_AFTER_FIRST_TURN);

    }

    private int movableDistanceByCamp(int movableDistance) {
        if (this.isBlack()) {
            return movableDistance;
        }
        return -movableDistance;
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
}
