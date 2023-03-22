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

        for (MovePattern movePattern : getMovePatterns()) {
            movablePositions.addAll(findMovablePositionsByMovePattern(source, board, movePattern));
        }
        return movablePositions;
    }

    private List<Position> findMovablePositionsByMovePattern(final Position source, final Board board,
                                                             final MovePattern movePattern) {
        List<Position> movablePositions = new ArrayList<>();

        Position nextPosition = source;
        while (canMove(board, movePattern, nextPosition)) {
            nextPosition = nextPosition.move(movePattern);
            movablePositions.add(nextPosition);
        }

        return movablePositions;
    }

    private boolean canMove(final Board board, final MovePattern movePattern,
                            final Position nextPosition) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        return isRangeValid(nextPosition, movePattern)
                && !isEnemy(nextSide)
                && isPossibleNextPosition(board, nextPosition, movePattern);
    }

    private boolean isEnemy(final Side nextSide) {
        return side != nextSide && !nextSide.isNeutrality();
    }

    private boolean isPossibleNextPosition(final Board board, final Position nextPosition, MovePattern movePattern) {
        return board.findSideByPosition(nextPosition.move(movePattern)) != this.side;
    }
}
