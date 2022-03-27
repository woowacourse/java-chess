package chess.domain.piece;

import chess.domain.postion.Position;

public class Pawn extends Piece{

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position Source, final Position target) {

    }
}
