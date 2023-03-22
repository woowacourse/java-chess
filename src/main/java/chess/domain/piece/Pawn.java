package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.PawnMovePattern;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {

    private final Type type;
    private final Side side;
    private final List<PawnMovePattern> movePatterns;
    private boolean notMoved;

    public Pawn(final Side side) {
        this.type = Type.PAWN;
        this.side = side;
        this.movePatterns = initMovePatterns();
        this.notMoved = true;
    }

    private List<PawnMovePattern> initMovePatterns() {
        List<PawnMovePattern> movePatterns = new ArrayList<>();
        if (side.isWhite()) {
            movePatterns.add(PawnMovePattern.UP);
            movePatterns.add(PawnMovePattern.LEFT_TOP);
            movePatterns.add(PawnMovePattern.RIGHT_TOP);
        }
        if (side.isBlack()) {
            movePatterns.add(PawnMovePattern.DOWN);
            movePatterns.add(PawnMovePattern.LEFT_BOTTOM);
            movePatterns.add(PawnMovePattern.RIGHT_BOTTOM);
        }
        return movePatterns;
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();

        for (final PawnMovePattern movePattern : movePatterns) {
            movablePositions.addAll(
                    findMovablePositionByMovePattern(source, movePattern, board)
            );
        }

        return movablePositions;
    }

    private List<Position> findMovablePositionByMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        if (movePattern.isDiagonalMove()) {
            return findMovablePositionByDiagonalMovePattern(source, movePattern, board);
        }
        return findMovablePositionByNonDiagonalMovePattern(source, movePattern, board);
    }

    private List<Position> findMovablePositionByDiagonalMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        List<Position> movablePosition = new ArrayList<>();
        if (canMoveMore(source, movePattern, board)) {
            final Position nextPosition = source.move(movePattern);
            movablePosition.add(nextPosition);
        }
        return movablePosition;
    }

    private boolean canMoveMore(final Position currentPosition, final PawnMovePattern movePattern, final Board board) {
        final Position nextPosition = currentPosition.move(movePattern);
        final Side currentSide = board.findSideByPosition(currentPosition);
        final Side nextSide = board.findSideByPosition(nextPosition);

        if (movePattern.isDiagonalMove()) {
            return canDiagonalMoveMore(currentPosition, nextPosition, currentSide, nextSide);
        }
        return canNonDiagonalMoveMore(currentPosition, nextPosition, nextSide);
    }

    private boolean canDiagonalMoveMore(
            final Position currentPosition,
            final Position nextPosition,
            final Side currentSide,
            final Side nextSide
    ) {
        return isInRange(currentPosition, nextPosition) && isEnemySide(currentSide, nextSide);
    }

    private boolean isInRange(final Position currentPosition, final Position nextPosition) {
        return currentPosition != nextPosition;
    }

    private boolean isEnemySide(final Side currentSide, final Side nextSide) {
        return !isNeutralitySide(nextSide) && currentSide != nextSide;
    }

    private boolean isNeutralitySide(final Side side) {
        return side.isNeutrality();
    }

    private boolean canNonDiagonalMoveMore(
            final Position currentPosition,
            final Position nextPosition,
            final Side nextSide
    ) {
        return isInRange(currentPosition, nextPosition) && isNeutralitySide(nextSide);
    }

    private List<Position> findMovablePositionByNonDiagonalMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        List<Position> movablePosition = new ArrayList<>();
        if (canMoveMore(source, movePattern, board)) {
            final Position nextPosition = source.move(movePattern);
            movablePosition.add(nextPosition);
        }
        if (notMoved && canMoveMoreSpecial(source, movePattern, board)) {
            final Position specialPosition = source.move(movePattern).move(movePattern);
            movablePosition.add(specialPosition);
        }
        return movablePosition;
    }

    private boolean canMoveMoreSpecial(final Position currentPosition, final PawnMovePattern movePattern,
                                       final Board board) {
        if (!canMoveMore(currentPosition, movePattern, board)) {
            return false;
        }
        return canMoveMore(currentPosition.move(movePattern), movePattern, board);
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
        return true;
    }

    @Override
    public void changePawnMoveState() {
        notMoved = false;
    }
}
