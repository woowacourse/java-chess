package chess.domain.piece;

public class King implements Piece{
    private final boolean isBlack;

    public King(boolean isBlack) {
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
            return "K";
        }
        return "k";
    }
}
