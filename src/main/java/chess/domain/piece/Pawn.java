package chess.domain.piece;

public class Pawn implements Piece {

    private final Team team;

    public Pawn(final Team team) {
        this.team = team;
    }
}
