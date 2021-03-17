package chess.domain.piece;

public class Pawn extends Piece{

    public Pawn(Boolean isBlack, char x, char y) {
        super(isBlack, x, y);
    }

    @Override
    void movable(char nextX, char nextY) {

    }

    @Override
    char getName() {
        return 0;
    }
}
