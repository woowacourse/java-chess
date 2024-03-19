package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public abstract class Piece {
    private final Position position;
    private final Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }
}
