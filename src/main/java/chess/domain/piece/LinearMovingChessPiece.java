package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public abstract class LinearMovingChessPiece implements ChessPiece {

    protected final List<Movement> directions;

    public LinearMovingChessPiece(List<Movement> directions) {
        this.directions = directions;
    }

    @Override
    public void move() {

    }
}
