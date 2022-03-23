package chess.piece;

import chess.Position;
import chess.Team;

public abstract class Piece {
    protected final Position position;
    protected final Team team;

    protected Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }


    public abstract void move(Position position);
}
