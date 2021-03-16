package chess.domain.piece;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";

    public Knight(Team team) {
        super(KNIGHT_NAME, team);
    }

    public void move(){}
}
