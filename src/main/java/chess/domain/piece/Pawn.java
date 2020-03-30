package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class Pawn extends Piece {
    public Pawn(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    public int getRow() {
        return position.getY();
    }

    @Override
    public double score() {
        return 1;
    }
}
