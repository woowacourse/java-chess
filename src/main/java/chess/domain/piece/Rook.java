package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class Rook extends Piece {
    public Rook(MoveStrategy moveStrategy,  char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new Rook(moveStrategy, representation, team, toPosition);
    }

    @Override
    public double score() {
        return 5;
    }
}
