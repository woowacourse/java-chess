package chess.domain.piece;

import chess.domain.Team;

public class Bishop extends Piece {

    public Bishop(Team team, PieceType pieceType) {
        super(team, pieceType);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
