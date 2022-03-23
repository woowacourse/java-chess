package chess.domain.piece;

import chess.domain.position.Position;

public final class King extends Piece {
    private final Team team;

    public King(Team team, Position position) {
        super(position);
        this.team = team;
    }
}
