package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class Knight extends Piece {
    public Knight(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new Knight(moveStrategy, representation, team, toPosition);
    }

    @Override
    public double score() {
        return 2.5;
    }
}
