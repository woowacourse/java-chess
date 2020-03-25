package chess.domain.chessPiece;

import chess.domain.MoveType;
import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }


    @Override
    public boolean isMovable(Position target, Piece targetPiece) {
        validSameTeam(targetPiece);
        return MoveType.of(position, target) == MoveType.STRAIGHT;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
