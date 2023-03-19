package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.MoveStrategy;
import chess.strategy.QueenStrategy;

public class Queen extends Piece {

    private final MoveStrategy moveStrategy;

    public Queen(Team team) {
        super(Role.QUEEN, team);
        moveStrategy = new QueenStrategy();
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }
}
