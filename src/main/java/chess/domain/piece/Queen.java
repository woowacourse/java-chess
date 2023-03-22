package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Queen extends NoneEmptyPiece {

    private Queen(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team, movement);
    }

    public static Queen from(Team team){
        return new Queen(PieceType.QUEEN, team, Movement.QUEEN);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        return movement.isMobile(relativePosition);
    }
}
