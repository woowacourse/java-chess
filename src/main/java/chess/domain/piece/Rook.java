package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.RookMoveStrategy;

public final class Rook extends AbstractPiece {
    public Rook(Team team) {
        super(new Name("R"), team, new RookMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetAbstractPiece, Position from, Position to) {
        return moveStrategy.canMove(team, targetAbstractPiece, from, to);
    }
}
