package chess.domain.piece;

import chess.domain.Position;
import chess.domain.move.CrossType;
import chess.domain.move.MoveType;
import chess.domain.move.StraightType;
import chess.domain.team.TeamStrategy;

public class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;

    public Queen(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return moveType instanceof StraightType || moveType instanceof CrossType;
    }

    @Override
    public String pieceName() {
        return teamStrategy.queenName();
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
    }
}
