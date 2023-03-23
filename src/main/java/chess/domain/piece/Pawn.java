package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.BlackPawnMovePattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.WhitePawnMovePattern;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Type type, final Side side) {
        super(type, side, initMovePatterns(side));
    }

    private static List<MovePattern> initMovePatterns(final Side side) {
        List<MovePattern> movePatterns = new ArrayList<>();

        if (side.isWhite()) {
            movePatterns.addAll(Arrays.asList(WhitePawnMovePattern.values()));
        }
        if (side.isBlack()) {
            movePatterns.addAll(Arrays.asList(BlackPawnMovePattern.values()));
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

        for (MovePattern movePattern : movePatterns) {
            movablePositions.addAll(findMovablePositionsByMovePattern(source, movePattern, board));
            movablePositions.addAll(findMovablePositionsByDoubleMove(source, board, movePattern));
        }
        return movablePositions;
    }

    private List<Position> findMovablePositionsByDoubleMove(final Position source, final Board board, final MovePattern movePattern) {
        List<Position> movablePositions = new ArrayList<>();

        if (canDoubleMove(source, board, movePattern)) {
            movablePositions.add(source.move(movePattern).move(movePattern));
        }
        return movablePositions;
    }

    private boolean canDoubleMove(final Position source, final Board board, final MovePattern movePattern) {
        return isRangeValid(source, movePattern) && !isDiagonal(source, source.move(movePattern))
                && isNeutrality(source, board, movePattern)
                && isRangeValid(source.move(movePattern), movePattern)
                && isNeutrality(source.move(movePattern), board, movePattern)
                && source.isDoubleMovePosition();
    }

    private static boolean isNeutrality(final Position source, final Board board, final MovePattern movePattern) {
        return board.findSideByPosition(source.move(movePattern)).isNeutrality();
    }

    private List<Position> findMovablePositionsByMovePattern(final Position source, final MovePattern movePattern,
                                                             final Board board) {
        List<Position> movablePositions = new ArrayList<>();
        Position nextPosition = getUpdatedPositionIfValidRange(source, movePattern);
        final Side nextSide = board.findSideByPosition(nextPosition);

        if (isDiagonal(source, nextPosition)) {
            checkDiagonalSide(movablePositions, nextPosition, nextSide);
            return movablePositions;
        }
        checkFrontSide(movablePositions, nextPosition, nextSide);
        return movablePositions;
    }

    private Position getUpdatedPositionIfValidRange(final Position source, final MovePattern movePattern) {
        Position nextPosition = source;
        if (isRangeValid(source, movePattern)) {
            nextPosition = nextPosition.move(movePattern);
        }
        return nextPosition;
    }

    private boolean isDiagonal(Position source, Position nextPosition) {
        return Math.abs((source.getRankIndex() - nextPosition.getRankIndex())) == Math.abs(
                (source.getFileIndex() - nextPosition.getFileIndex()));
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