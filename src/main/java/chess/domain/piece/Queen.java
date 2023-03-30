package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class Queen extends Piece {

    public Queen(Team team) {
        super(Role.QUEEN, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target);
    }
}
