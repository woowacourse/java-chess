package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Pawn extends ColoredPiece {

    public Pawn(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
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
