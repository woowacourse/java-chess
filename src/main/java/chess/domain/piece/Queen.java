package chess.domain.piece;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";

    public Queen(Team team) {
        super(QUEEN_NAME, team);
    }

    public void move(){}
}
