package chess.domain.piece;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";

    public Bishop(Team team) {
        super(BISHOP_NAME, team);
    }

    public void move(){}
}
