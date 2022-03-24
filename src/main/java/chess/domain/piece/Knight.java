package chess.domain.piece;

import chess.domain.state.Direction;

public class Knight extends Piece{

    public Knight(Team team) {
        super(team, Name.KNIGHT);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }
}
