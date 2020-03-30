package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public class WhitePawn extends Pawn {
    public WhitePawn(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        super(moveStrategy, representation, team, position);
    }

    @Override
    public Piece movedPiece(Position toPosition) {
        return new WhitePawn(moveStrategy, representation, team, toPosition);
    }
}
