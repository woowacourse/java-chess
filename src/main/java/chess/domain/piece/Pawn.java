package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Side;
import chess.domain.Type;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.PawnMovePattern;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final List<MovePattern> movePatterns;
    private final boolean moved;

    public Pawn(final Type type, final Side side) {
        super(type, side);
        //TODO this 추가
        movePatterns = initMovePatterns(side);
        moved = false;
    }

    private List<MovePattern> initMovePatterns(final Side side) {
        List<MovePattern> movePatterns = new ArrayList<>();
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
    protected void validate(final Type type, final Side side) {
        validateType(type);
        validateSide(side);
    }

    @Override
    public List<Position> findMovablePositions(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();
        final List<MovePattern> movePatterns = getMovePatterns();
        for (MovePattern movePattern : movePatterns) {
            Position nextPosition = source;
            if (isRangeValid(nextPosition, movePattern)) {
                nextPosition = nextPosition.move(movePattern);
                checkSide(movablePositions, source, nextPosition, board);
            }
            if (!isDiagonal(source, source.move(movePattern)) && isRangeValid(source, movePattern) && isRangeValid(
                    source.move(movePattern), movePattern) && !moved) {
                movablePositions.add(source.move(movePattern).move(movePattern));
            }
        }
        return movablePositions;
    }

    private void checkSide(final List<Position> movablePositions, final Position source, final Position nextPosition,
                           final Board board) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        if (isDiagonal(source, nextPosition)) {
            checkDiagonalSide(movablePositions, nextPosition, nextSide);
            return;
        }
        checkFrontSide(movablePositions, nextPosition, nextSide);
    }

    private boolean isDiagonal(Position source, Position nextPosition) {
        return Math.abs((source.getRankIndex() - nextPosition.getRankIndex())) == Math.abs((source.getFileIndex() - nextPosition.getFileIndex()));
    }

    private void checkFrontSide(final List<Position> movablePositions, final Position nextPosition,
                                final Side nextSide) {
        if (nextSide == Side.NEUTRALITY) {
            movablePositions.add(nextPosition);
        }
    }

    private void checkDiagonalSide(final List<Position> movablePositions, final Position nextPosition,
                                   final Side nextSide) {
        if (nextSide != this.side && nextSide != Side.NEUTRALITY) {
            movablePositions.add(nextPosition);
        }
    }

    private boolean isRangeValid(final Position position, final MovePattern movePattern) {
        final int nextRank = position.getRankIndex() + movePattern.getRankVector();
        final int nextFile = position.getFileIndex() + movePattern.getFileVector();
        return nextRank >= 1 && nextRank <= 8 && nextFile >= 1 && nextFile <= 8;
    }

    @Override
    protected List<MovePattern> getMovePatterns() {
        return movePatterns;
    }

    private void validateType(final Type type) {
        if (type != Type.PAWN) {
            throw new IllegalArgumentException("폰의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("폰은 중립적인 기물이 아닙니다.");
        }
    }
}
