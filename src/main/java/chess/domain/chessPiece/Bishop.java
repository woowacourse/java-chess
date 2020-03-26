package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.Cross;
import chess.domain.movefactory.MoveFactory;

public class Bishop extends Piece {
    public Bishop(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Position targetPosition, Piece targetPiece) {
        validSameTeam(targetPiece);
        return MoveFactory.of(position, targetPosition) instanceof Cross;
    }

    @Override
    public String pieceName() {
        return teamStrategy.bishopName();
    }
}
