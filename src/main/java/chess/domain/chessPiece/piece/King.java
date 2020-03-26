package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.CrossType;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.StraightType;

public class King extends Piece {
    private static final int KING_SCORE = 0;

    private final double score = KING_SCORE;

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
        return score;
    }
}
