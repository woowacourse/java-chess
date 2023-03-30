package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(Role.BISHOP, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target);
    }
}
