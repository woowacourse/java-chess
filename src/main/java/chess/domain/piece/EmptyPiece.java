package chess.domain.piece;

import chess.domain.state.Direction;

public class EmptyPiece extends Piece{

    public EmptyPiece() {
        super(Team.NONE, Name.NONE);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }
}
