package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Knight extends NoneEmptyPiece {

    private Knight(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team, movement);
    }

    public static Knight from(Team team){
        return new Knight(PieceType.KNIGHT, team, Movement.KNIGHT);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        return movement.isMobile(relativePosition);
    }

}
