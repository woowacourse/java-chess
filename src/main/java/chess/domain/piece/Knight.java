package chess.domain.piece;

import chess.domain.Team;

public class Knight extends Piece {

    public Knight(Team team, PieceType pieceType) {
        super(team, pieceType);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
