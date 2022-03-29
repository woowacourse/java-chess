package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.KnightMoveStrategy;

public final class Knight extends AbstractPiece {
    public Knight(Team team) {
        super(new Name("N"), team, new KnightMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.canMove(team, targetPiece, from, to);
    }
}
