package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.KnightStrategy;

public class Knight extends Piece {

    public Knight(Team team) {
        super(Role.KNIGHT, team, new KnightStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
