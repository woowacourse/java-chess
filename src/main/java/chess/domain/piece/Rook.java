package chess.domain.piece;

public class Rook implements Piece{
    private final boolean isBlack;

    public Rook(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public void strategy() {

    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (isBlack) {
            return "R";
        }
        return "r";
    }
}
