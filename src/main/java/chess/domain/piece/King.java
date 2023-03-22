package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class King extends NoneEmptyPiece {

    private King(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team, movement);
    }

    public static King from(Team team){
        return new King(PieceType.KING, team, Movement.KING);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        return movement.isMobile(relativePosition);
    }
}
