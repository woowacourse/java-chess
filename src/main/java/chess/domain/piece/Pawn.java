package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(final Camp camp) {
        super(camp, Role.PAWN);
        this.possibleMoves = makePossibleMove();
        this.isMoved = false;
    }

    public Pawn(final Camp camp, final boolean isMoved) {
        super(camp, Role.PAWN);
        this.possibleMoves = makePossibleMove();
        this.isMoved = isMoved;
    }

    private List<Move> makePossibleMove() {
        if (camp.equals(Camp.WHITE)) {
            return List.of(
                    Move.UP,
                    Move.UP_RIGHT,
                    Move.UP_LEFT
            );
        }

        return List.of(
                Move.DOWN,
                Move.DOWN_RIGHT,
                Move.DOWN_LEFT
        );
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        if (possibleMoves.contains(move)) {
            return isMovableContainPossibleMoves(source, target, move);
        }
        return false;
    }

    private boolean isMovableContainPossibleMoves(final Square source, final Square target, final Move move) {
        final boolean isMovableOneStep = source.isMovableToTarget(target, move.getFile(), move.getRank());
        final boolean isMovableTwoStep = source.isMovableToTarget(target, move.getFile(),
                move.getRank() + camp.calculateDirection(1));
        if (Move.isMoveForward(move) && !isMoved) {
            return isMovableOneStep || isMovableTwoStep;
        }
        return isMovableOneStep;
    }
}
