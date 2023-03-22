package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
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
