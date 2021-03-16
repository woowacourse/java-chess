package chess.domain.piece;

public class King extends Piece{
    public King(Boolean isBlack, char horizontal, char vertical) {
        super(isBlack, horizontal, vertical);
    }

    @Override
    void move() {

    }

    @Override
    boolean isMovable() {
        return false;
    }

    @Override
    char getName() {
        if(isBlack()){
            return 'K';
        }
        return 'k';
    }
}
