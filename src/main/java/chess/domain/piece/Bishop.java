package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Bishop extends NoneEmptyPiece {

    private Bishop(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team, movement);
    }

    public static Bishop from(Team team){
        return new Bishop(PieceType.BISHOP, team, Movement.BISHOP);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }
}
