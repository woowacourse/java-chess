package chessrefactor.piece;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "q";
    }
}
