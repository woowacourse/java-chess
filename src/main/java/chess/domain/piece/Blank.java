package chess.domain.piece;

public class Blank extends Piece {

    public Blank() {
        super(Color.NONE);
    }

    @Override
    public boolean isBlank() {
        return true;
    }
}
