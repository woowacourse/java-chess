package chess.domain.piece;

public class Blank extends Piece {

    private static final String SIGNATURE = ".";

    public Blank(Position position) {
        super(position, SIGNATURE);
    }
}
