package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public class Queen extends ColoredPiece {
    public Queen(TeamStrategy teamStrategy) {
        super(teamStrategy);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.queenName();
    }
}
