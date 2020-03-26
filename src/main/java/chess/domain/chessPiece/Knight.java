package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Knight extends Piece {

    public Knight(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position targetPosition) {
        validSameTeam(targetPiece);
        return true;
        //TODO 미구현 ;
    }

    @Override
    public String pieceName() {
        return teamStrategy.knightName();
    }
}
