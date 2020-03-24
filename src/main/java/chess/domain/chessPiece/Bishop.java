package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public class Bishop extends ColoredPiece {
    public Bishop(TeamStrategy teamStrategy) {
        super(teamStrategy);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.bishopName();
    }
}
