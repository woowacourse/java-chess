package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.CrossType;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.StraightType;

public class Queen extends Piece {
    private final double score = 9;
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
        return score;
    }
}
