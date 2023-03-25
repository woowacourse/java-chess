package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.BishopStrategy;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(Role.BISHOP, team, new BishopStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
