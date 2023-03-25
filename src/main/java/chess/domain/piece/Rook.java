package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class Rook extends Piece {

    public Rook(Team team) {
        super(Role.ROOK, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target);
    }
}
