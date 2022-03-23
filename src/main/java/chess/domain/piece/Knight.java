package chess.domain.piece;

import chess.domain.position.Position;

public final class Knight extends Piece {

    private final Team team;

    public Knight(Team team,Position position) {
        super(position);
        this.team = team;
    }
}
