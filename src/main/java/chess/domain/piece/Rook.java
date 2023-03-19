package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.CrossStrategy;
import chess.strategy.MoveStrategy;

public class Rook extends Piece {

    private final MoveStrategy moveStrategy;

    public Rook(Team team) {
        super(Role.ROOK, team);
        moveStrategy = new CrossStrategy();
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }
}
