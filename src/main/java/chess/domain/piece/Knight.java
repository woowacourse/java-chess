package chess.domain.piece;

import chess.domain.Position;
import chess.domain.move.KnightType;
import chess.domain.move.MoveType;
import chess.domain.team.TeamStrategy;

public class Knight extends Piece {
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return moveType instanceof KnightType;
    }

    @Override
    public String pieceName() {
        return teamStrategy.knightName();
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
    }
}
