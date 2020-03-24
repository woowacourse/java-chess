package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public class Knight extends ColoredPiece {
    public Knight(TeamStrategy teamStrategy) {
        super(teamStrategy);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.kingName();
    }
}
