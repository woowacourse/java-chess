package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class Piece {
    private final Team team;
    private final Position position;
    private final MoveStrategy moveStrategy;

    public Piece(Position position, MoveStrategy moveStrategy, Team team) {
        this.position = position;
        this.moveStrategy = moveStrategy;
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void canMove(Position source, Position target) {
        moveStrategy.isMovable(source, target);
    }

    public abstract String getSymbol();

    public Team getTeam() {
        return team;
    }
}
