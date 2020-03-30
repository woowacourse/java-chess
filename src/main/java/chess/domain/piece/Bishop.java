package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class Bishop extends Piece {
    public Bishop(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new Bishop(moveStrategy, representation, team, toPosition);
    }

    @Override
    public double score() {
        return 3;
    }
}
