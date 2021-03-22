package chess.domain.piece;

import chess.domain.order.MoveOrder;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.MoveStrategy;

public abstract class Piece {
    private final Notation notation;
    private final MoveStrategy moveStrategy;

    public Piece(Notation notation, MoveStrategy moveStrategy) {
        this.notation = notation;
        this.moveStrategy = moveStrategy;
    }

    public String getNotationText() {
        return notation.getNotationText();
    }

    public boolean canMove(MoveOrder moveOrder) {
        return this.moveStrategy.canMove(moveOrder);
    }

    public boolean isNotBlank(){
        return !this.equals(Blank.getInstance());
    }
}
