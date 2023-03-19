package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.DiagonalStrategy;
import chess.strategy.MoveStrategy;

public class Bishop extends Piece {

    private final MoveStrategy moveStrategy;

    public Bishop(Team team) {
        super(Role.BISHOP, team);
        moveStrategy = new DiagonalStrategy();
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }
}
