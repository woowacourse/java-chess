package chess.domain.piece;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";

    public Pawn(Team team) {
        super(PAWN_NAME, team);
    }

    public void move(){}
}
