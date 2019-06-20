package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public abstract class Piece {
    protected Position position;
    protected Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }
}
