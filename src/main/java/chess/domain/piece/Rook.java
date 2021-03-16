package chess.domain.piece;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";

    public Rook(Team team) {
        super(ROOK_NAME, team);
    }

    public void move(){}
}
