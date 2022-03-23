package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    private final Team team;

    public Pawn(Team team, Position position) {
        super(position);
        this.team =team;
    }
}
