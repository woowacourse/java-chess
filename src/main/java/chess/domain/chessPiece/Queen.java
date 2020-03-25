package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Queen extends Piece {

    public Queen(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Position target, Piece targetPiece) {
        validSameTeam(targetPiece);
        return true;
    }

    @Override
    public String pieceName() {
        return teamStrategy.queenName();
    }
}
