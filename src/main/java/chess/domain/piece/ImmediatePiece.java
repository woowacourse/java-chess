package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class ImmediatePiece extends Piece {

    public ImmediatePiece(final Type type, final Side side, List<MovePattern> movePatterns) {
        super(type, side, movePatterns);
    }

    @Override
    public List<Position> findMovablePositions(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();

        for (MovePattern movePattern : movePatterns) {
            movablePositions.addAll(findMovablePositionsByMovePattern(source, board, movePattern));
        }
        return movablePositions;
    }

    private List<Position> findMovablePositionsByMovePattern(final Position source,
                                                             final Board board, final MovePattern movePattern) {
        List<Position> movablePositions = new ArrayList<>();
        
        Position nextPosition = source;
        if (isRangeValid(nextPosition, movePattern)) {
            nextPosition = nextPosition.move(movePattern);
            checkSide(movablePositions, nextPosition, board);
        }
        return movablePositions;
    }

    private void checkSide(final List<Position> movablePositions, final Position nextPosition, final Board board) {
        final Side nextSide = board.findSideByPosition(nextPosition);
        if (nextSide != this.side) {
            movablePositions.add(nextPosition);
        }
    }
}
