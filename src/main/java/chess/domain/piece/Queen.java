package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class Queen extends Piece {
    public Queen(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new Queen(moveStrategy, representation, team, toPosition);
    }

    @Override
    public double score() {
        return 9;
    }
}
