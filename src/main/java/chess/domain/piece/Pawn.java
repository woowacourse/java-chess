package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.moveStrategy.PawnStrategy;

import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";
    private static final double SCORE = 1;

    public Pawn(Team team) {
        super(PAWN_NAME, team, SCORE, new PawnStrategy(), true);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        if (isBlack()) {
            return movablePositions(target, Direction.pawnBlack(isFirstStep(target)));
        }
        return movablePositions(target, Direction.pawnWhite(isFirstStep(target)));
    }

    private boolean isFirstStep(Position position) {
        if (isBlack() && position.isSameVertical(Vertical.SEVEN)) {
            return true;
        }
        return isWhite() && position.isSameVertical(Vertical.TWO);
    }
}
