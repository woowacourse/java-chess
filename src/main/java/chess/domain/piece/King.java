package chess.domain.piece;

import chess.domain.postion.Position;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public void canMove(Position Source, Position target) {

    }
}
