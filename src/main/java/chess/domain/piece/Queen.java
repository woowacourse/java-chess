package chess.domain.piece;

import chess.domain.state.Direction;

public class Queen extends Piece{

    public Queen(Team team) {
        super(team, Name.QUEEN);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }
}
