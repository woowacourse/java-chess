package chessrefactor.piece;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "k";
    }
}
