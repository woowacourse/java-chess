package chess.domain.piece;

import chess.domain.position.Position;

public final class Queen extends Piece {
    private final Team team;


    public Queen(Team team, Position position) {
        super(position);
        this.team = team;
    }
}
