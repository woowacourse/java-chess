package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(final Team team) {
        super(team, Role.PAWN);
        this.possibleMoves = this.makePossibleMove();
        this.isMoved = false;
    }

    public Pawn(final Team team, final boolean isMoved) {
        super(team, Role.PAWN);
        this.possibleMoves = this.makePossibleMove();
        this.isMoved = isMoved;
    }

    private List<Move> makePossibleMove() {
        if (this.team.equals(Team.WHITE)) {
            return List.of(Move.UP, Move.RIGHT_UP, Move.LEFT_UP);
        }
        return List.of(Move.DOWN, Move.RIGHT_DOWN, Move.LEFT_DOWN);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        if (this.possibleMoves.contains(move)) {
            return this.isMovableContainPossibleMoves(source, target, move);
        }
        return false;
    }

    private boolean isMovableContainPossibleMoves(final Square source, final Square target, final Move move) {
        final boolean isMovableOneStep = source.isMovableToTarget(target, move.getFile(), move.getRank());
        final boolean isMovableTwoStep = source.isMovableToTarget(target, move.getFile(),
                move.getRank() + this.team.calculateDirection(1));
        if (Move.isMoveForward(move) && !this.isMoved) {
            return isMovableOneStep || isMovableTwoStep;
        }
        return isMovableOneStep;
    }
}
