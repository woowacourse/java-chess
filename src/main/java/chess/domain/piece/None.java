package chess.domain.piece;

public class None implements ChessPiece {

    @Override
    public void move() {

    }

    @Override
    public String name() {
        return "-";
    }
}
