package chess.domain.piece;

import chess.domain.Team;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
