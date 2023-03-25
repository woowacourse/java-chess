package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class King extends Piece {

    public King(Team team) {
        super(Role.KING, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target);
    }
}
