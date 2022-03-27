package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.postion.Position;

public abstract class Piece {
    private final Team team;
    private final MoveStrategy moveStrategy;

    public Piece(final Team team, final MoveStrategy moveStrategy) {
        this.team = team;
        this.moveStrategy = moveStrategy;
    }

    public void canMove(final Position source, final Position target){
       moveStrategy.isMovable(source, target);
    }

    abstract String symbol();
}
