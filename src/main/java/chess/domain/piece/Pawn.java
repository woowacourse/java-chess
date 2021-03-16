package chess.domain.piece;

public class Pawn implements Piece{
    private final boolean isBlack;

    public Pawn(boolean isBlack) {
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
            return "P";
        }
        return "p";
    }
}
