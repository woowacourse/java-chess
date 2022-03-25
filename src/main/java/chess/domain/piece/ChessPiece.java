package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public abstract class ChessPiece {
    protected final String name;
    protected final Team team;
    protected ChessBoardPosition position;

    protected ChessPiece(String name, Team team, ChessBoardPosition position) {
        this.name = name;
        this.team = team;
        this.position = position;
    }

    abstract void move(ChessBoardPosition targetPosition);

    public String getName() {
        return name;
    }

    public ChessBoardPosition getPosition() {
        return position;
    }

    public boolean isSamePosition(ChessBoardPosition other) {
        return position.equals(other);
    }
}
