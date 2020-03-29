package chess.domain.piece;

import chess.domain.Position;
import chess.domain.move.CrossType;
import chess.domain.move.MoveType;
import chess.domain.move.StraightType;
import chess.domain.team.TeamStrategy;

public class King extends Piece {
    private static final int KING_SCORE = 0;

    public King(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return (moveType instanceof StraightType || moveType instanceof CrossType) && moveType.getCount() == 1;
    }

    @Override
    public String pieceName() {
        return teamStrategy.kingName();
    }

    @Override
    public double getScore() {
        return KING_SCORE;
    }
}
