package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class Knight extends Piece {

    public Knight(Team team) {
        super(Role.KNIGHT, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target);
    }
}
