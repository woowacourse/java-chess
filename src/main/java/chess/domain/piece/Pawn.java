package chess.domain.piece;

import chess.domain.state.Direction;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        if (isBlack()) {
            return Direction.isBlackPawnDirection(direction);
        }
        return Direction.isWhitePawnDirection(direction);
    }
}
