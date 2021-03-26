package chess.domain.piece;

import chess.domain.order.MoveRoute;
import chess.domain.piece.strategy.MoveStrategy;

public abstract class Piece {
    private final String notation;
    private final MoveStrategy moveStrategy;

    public Piece(String notation, MoveStrategy moveStrategy) {
        this.notation = notation;
        this.moveStrategy = moveStrategy;
    }

    public String getNotation() {
        return notation;
    }

    public boolean canMove(MoveRoute moveRoute) {
        return this.moveStrategy.canMove(moveRoute);
    }

    public boolean isBlank() {
        return this.equals(new Blank());
    }

    public boolean isNotBlank() {
        return !this.equals(new Blank());
    }
}
