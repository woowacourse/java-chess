package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.BishopMoveStrategy;

public final class Bishop extends Piece {

    public Bishop(Team team) {
        super(new Name("B"), team, new BishopMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.canMove(team, targetPiece, from, to);
    }
}
