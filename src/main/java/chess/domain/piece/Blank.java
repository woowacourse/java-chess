package chess.domain.piece;

public class Blank extends Piece{

    public Blank(Color color) {
        super(color);
        this.type = Type.BLANK;
    }
}
