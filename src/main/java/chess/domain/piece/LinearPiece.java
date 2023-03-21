package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public final class LinearPiece implements Piece {

    private final Type type;
    private final Side side;
    private final List<MovePattern> movePatterns;

    public LinearPiece(final Type type, final Side side, final List<MovePattern> movePatterns) {
        this.type = type;
        this.side = side;
        this.movePatterns = movePatterns;
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();

        for (final MovePattern movePattern : movePatterns) {
            movablePositions.addAll(
                    findMovablePositionByMovePattern(source, movePattern, board)
            );
        }

        return movablePositions;
    }

    private List<Position> findMovablePositionByMovePattern(
            final Position source,
            final MovePattern movePattern,
            final Board board) {

        List<Position> movablePosition = new ArrayList<>();
        Position currentPosition = source;
        while (canMoveMore(currentPosition, movePattern, board)) {
            Position nextPosition = currentPosition.move(movePattern);
            movablePosition.add(nextPosition);
            currentPosition = nextPosition;
        }
        return movablePosition;
    }

    private boolean canMoveMore(final Position currentPosition, final MovePattern movePattern, final Board board) {
        final Position nextPosition = currentPosition.move(movePattern);
        final Side currentSide = board.findSideByPosition(currentPosition);
        final Side nextSide = board.findSideByPosition(nextPosition);

        return isInRange(currentPosition, nextPosition) &&
                isDifferentSide(currentSide, nextSide) &&
                !hasCaptured(currentSide, nextSide);
    }

    private boolean isInRange(final Position currentPosition, final Position nextPosition) {
        return currentPosition != nextPosition;
    }

    private boolean isDifferentSide(final Side currentSide, final Side nextSide) {
        return currentSide != nextSide;
    }

    private boolean hasCaptured(final Side currentSide, final Side nextSide) {
        return isDifferentSide(currentSide, nextSide) && nextSide != Side.NEUTRALITY;
    }

    @Override
    public String name() {
        return type.getSymbol(side);
    }

    @Override
    public Side side() {
        return side;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public void changePawnMoveState() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
