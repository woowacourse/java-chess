package chess.domain.piece;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class Pawn extends Piece {
    private static final String ERROR_CANT_MOVE = "폰이 이동할 수 없는 위치입니다.";
    private static final String ERROR_CANT_CAPTURE = "폰이 잡을 수 없는 위치입니다.";
    private static final int DISTANCE_ADDITIONAL_IN_FIRST_MOVE = 1;
    private static final double SCORE = 1;

    private boolean firstMove;

    public Pawn(Camp camp) {
        super(camp, Type.PAWN);
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
        if (!sourcePosition.inSameColumnWith(targetPosition)) {
            return false;
        }
        int rowDirectedDistance = targetPosition.rowDirectedDistance(sourcePosition);
        if (!checkDirection(rowDirectedDistance)) {
            return false;
        }
        if (firstMove && checkFirstMove(sourcePosition, targetPosition)) {
            return true;
        }
        return super.canApproach(sourcePosition, targetPosition);
    }

    private boolean checkFirstMove(Position sourcePosition, Position targetPosition) {
        if (this.isCamp(BLACK)) {
            return super.canApproach(sourcePosition,
                    targetPosition.goDown(BLACK.giveVerticalDirectionTo(DISTANCE_ADDITIONAL_IN_FIRST_MOVE)));
        }
        return super.canApproach(sourcePosition,
                targetPosition.goDown(WHITE.giveVerticalDirectionTo(DISTANCE_ADDITIONAL_IN_FIRST_MOVE)));
    }

    private boolean canCapture(Position sourcePosition, Position targetPosition) {
        if (!sourcePosition.inDiagonalWith(targetPosition)) {
            return false;
        }
        int rowDirectedDistance = targetPosition.rowDirectedDistance(sourcePosition);
        return checkDirection(rowDirectedDistance) && super.canApproach(sourcePosition, targetPosition);
    }

    private boolean checkDirection(int distance) {
        int undirectedDistance = Math.abs(distance);
        if (this.isCamp(BLACK)) {
            return BLACK.giveVerticalDirectionTo(undirectedDistance) == distance;
        }
        return WHITE.giveVerticalDirectionTo(undirectedDistance) == distance;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
