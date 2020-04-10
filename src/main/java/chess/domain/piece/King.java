package chess.domain.piece;

import chess.domain.move.CrossMove;
import chess.domain.move.Move;
import chess.domain.move.StraightMove;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.TeamStrategy;

import java.util.List;

public class King extends Piece {
    private static final int KING_MOVABLE_RANGE = 1;

    public King(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    protected boolean isMovablePattern(Move move, Position targetPosition, List<Piece> pieces) {
        return isKingMovable(move);
    }

    private boolean isKingMovable(Move move) {
        return (move instanceof StraightMove || move instanceof CrossMove)
                && move.getCount() == KING_MOVABLE_RANGE;
    }

    @Override
    public String getPieceName() {
        return teamStrategy.kingName();
    }
}
