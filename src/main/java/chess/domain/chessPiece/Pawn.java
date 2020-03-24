package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public class Pawn extends ColoredPiece {
    public Pawn(TeamStrategy teamStrategy) {
        super(teamStrategy);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.pawnName();
    }
}
