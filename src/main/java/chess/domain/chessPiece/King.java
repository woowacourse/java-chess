package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class King extends Piece {

    public King(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position targetPosition) {
        validSameTeam(targetPiece);
        return Math.abs(position.calculateFileDistance(targetPosition)) <= 1
                && Math.abs(position.calculateRankDistance(targetPosition)) <= 1;
    }

    @Override
    public String pieceName() {
        return teamStrategy.kingName();
    }
}
