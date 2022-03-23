package chess.domain.piece;

import chess.domain.position.Position;

public final class Bishop extends Piece {

    private final Team team;

    public Bishop(Team team, Position position) {
        super(position);
        this.team =team;
    }
}
