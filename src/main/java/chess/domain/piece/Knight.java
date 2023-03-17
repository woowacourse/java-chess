package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.movepattern.KnightMovePattern;
import chess.domain.movepattern.MovePattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {

    private final List<MovePattern> movePatterns;

    public Knight(final Type type, final Side side) {
        super(type, side);
        movePatterns = Arrays.asList(KnightMovePattern.values());
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
                checkSide(movablePositions, nextPosition, board);
            }
        }
        return movablePositions;
    }

    private void checkSide(final List<Position> movablePositions, final Position nextPosition, final Board board) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        if (nextSide != this.side) {
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
        if (type != Type.KNIGHT) {
            throw new IllegalArgumentException("나이트의 타입이 잘못되었습니다.");
        }
    }

    private void validateSide(final Side side) {
        if (side == Side.NEUTRALITY) {
            throw new IllegalArgumentException("나이트는 중립적인 기물이 아닙니다.");
        }
    }
}
