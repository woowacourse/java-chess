package chess.domain.piece;

public class King extends Piece {
    private static final String KING_NAME = "K";

    public King(Team team) {
        super(KING_NAME, team);
    }

    public void move(){}
}
