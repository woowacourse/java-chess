package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(Camp camp) {
        super(camp, Role.PAWN);
        possibleMoves = makePossibleMove();
        isMoved = false;
    }

    public Pawn(Camp camp, boolean isMoved) {
        super(camp, Role.PAWN);
        possibleMoves = makePossibleMove();
        this.isMoved = isMoved;
    }

    private List<Move> makePossibleMove() {
        if (camp.equals(Camp.WHITE)) {
            return List.of(Move.UP, Move.RIGHT_UP, Move.LEFT_UP);
        }
        return List.of(Move.DOWN, Move.RIGHT_DOWN, Move.LEFT_DOWN);
    }

    @Override
    public boolean isMovable(Square source, Square target, Move move) {
        if (possibleMoves.contains(move)) {
            return isMovableContainPossibleMoves(source, target, move);
        }
        return false;
    }

    private boolean isMovableContainPossibleMoves(Square source, Square target, Move move) {
        boolean isMovableOneStep = source.isMovableToTarget(target, move.getFile(), move.getRank());
        boolean isMovableTwoStep = source.isMovableToTarget(target, move.getFile(),
                move.getRank() + camp.calculateDirection(1));
        if (Move.isMoveForward(move) && !isMoved) {
            return isMovableOneStep || isMovableTwoStep;
        }
        return isMovableOneStep;
    }
}
