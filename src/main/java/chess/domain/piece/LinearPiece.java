package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class LinearPiece extends Piece {

    public LinearPiece(final Type type, final Side side) {
        super(type, side);
    }

    @Override
    public List<Position> findMovablePositions(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();
        final List<MovePattern> movePatterns = getMovePatterns();
        for (MovePattern movePattern : movePatterns) {
            Position nextPosition = source;
            boolean killFlag = false;
            while (canMove(board, movePattern, nextPosition, killFlag)) {
                nextPosition = nextPosition.move(movePattern);
                killFlag = checkSide(movablePositions, nextPosition, board);
            }
        }
        return movablePositions;
    }

    private boolean canMove(final Board board, final MovePattern movePattern,
                            final Position nextPosition, final boolean killFlag) {
        return isRangeValid(nextPosition, movePattern)
                && !killFlag
                && isPossibleNextPosition(board, nextPosition, movePattern);
    }

    private boolean checkSide(final List<Position> movablePositions, final Position nextPosition, final Board board) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        if (nextSide != this.side) {
            movablePositions.add(nextPosition);
        }
        return nextSide != this.side && nextSide != Side.NEUTRALITY;
    }

    private boolean isPossibleNextPosition(final Board board, final Position nextPosition, MovePattern movePattern) {
        return board.findSideByPosition(nextPosition.move(movePattern)) != this.side;
    }
}
