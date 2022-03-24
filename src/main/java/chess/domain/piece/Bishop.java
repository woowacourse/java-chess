package chess.domain.piece;

import chess.domain.state.Direction;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team, Name.BISHOP);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }
}
