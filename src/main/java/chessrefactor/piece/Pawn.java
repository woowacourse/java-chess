package chessrefactor.piece;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "p";
    }
}
