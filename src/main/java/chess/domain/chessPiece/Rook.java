package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public class Rook extends ColoredPiece {
    public Rook(TeamStrategy teamStrategy) {
        super(teamStrategy);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
