package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Rook extends NoneEmptyPiece {

    private Rook(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team, movement);
    }

    public static Rook from(Team team){
        return new Rook(PieceType.ROOK, team, Movement.ROOK);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }
}
