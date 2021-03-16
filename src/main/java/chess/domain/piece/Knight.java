package chess.domain.piece;

public class Knight implements Piece{
    private final boolean isBlack;

    public Knight(boolean isBlack) {
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
            return "N";
        }
        return "n";
    }
}
