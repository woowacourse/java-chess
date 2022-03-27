package chess.domain.piece;

import chess.domain.postion.Position;

public class Queen extends Piece{

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position Source, final Position target) {

    }
}
