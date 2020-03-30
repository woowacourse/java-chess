package chess.domain.piece;

import chess.domain.Position;
import chess.domain.move.CrossType;
import chess.domain.move.MoveType;
import chess.domain.team.TeamStrategy;

public class Bishop extends Piece {
    private static final int BISHOP_SCORE = 3;

    public Bishop(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return moveType instanceof CrossType;
    }

    @Override
    public String pieceName() {
        return teamStrategy.bishopName();
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }
}
