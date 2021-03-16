package chess.domain.piece;

public class Bishop implements Piece{
    private final boolean isBlack;

    public Bishop(boolean isBlack) {
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
            return "B";
        }
        return "b";
    }
}
