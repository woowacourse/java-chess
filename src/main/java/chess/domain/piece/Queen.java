package chess.domain.piece;

public class Queen implements Piece{
    private final boolean isBlack;

    public Queen(boolean isBlack) {
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
            return "Q";
        }
        return "q";
    }
}
