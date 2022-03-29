package chessrefactor.piece;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return ".";
    }
}
