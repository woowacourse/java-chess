package chess.domain.piece;

import chess.domain.order.MoveOrder;
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

    public boolean canMove(MoveOrder moveOrder) {
        return this.moveStrategy.canMove(moveOrder);
    }

    public boolean isNotBlank(){
        return !this.equals(new Blank());
    }
}
