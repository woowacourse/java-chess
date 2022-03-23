package chess.domain.piece;

import chess.domain.position.Position;

public final class Rook extends Piece {
    private final Team team;

    public Rook(Team team, Position position) {
        super(position);
        this.team = team;
    }
}
