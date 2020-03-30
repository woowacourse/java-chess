package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class BlackPawn extends Pawn {
    public BlackPawn(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new BlackPawn(moveStrategy, representation, team, toPosition);
    }
}
