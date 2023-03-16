package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Side;
import chess.domain.Type;
import chess.domain.movepattern.MovePattern;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Type type;
    private final Side side;

    public Piece(final Type type, final Side side) {
        validate(type, side);
        this.type = type;
        this.side = side;
    }

    public String getName() {
        return type.getSymbol(side);
    }

    public Side getSide() {
        return side;
    }

    protected abstract void validate(final Type type, final Side side);

    protected abstract List<MovePattern> getMovePatterns();

    public List<Position> findMovablePositions(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();
        final List<MovePattern> movePatterns = getMovePatterns();
        for (MovePattern movePattern : movePatterns) {
            Position nextPosition = source;
            boolean killFlag = false;
            while (isRangeValid(nextPosition, movePattern) && !killFlag) {
                nextPosition = nextPosition.move(movePattern);
                killFlag = checkSide(movablePositions, nextPosition, board);
            }
        }
        return movablePositions;
    }

    private boolean checkSide(final List<Position> movablePositions, final Position nextPosition, final Board board) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        if (nextSide != this.side) {
            movablePositions.add(nextPosition);
        }
        return nextSide != this.side && nextSide != Side.NEUTRALITY;
    }

    private boolean isRangeValid(final Position position, final MovePattern movePattern) {
        final int nextRank = position.getRank() + movePattern.getRankVector();
        final int nextFile = position.getFile() + movePattern.getFileVector();
        return nextRank >= 1 && nextRank <= 8 && nextFile >= 1 && nextFile <= 8;
    }
}
