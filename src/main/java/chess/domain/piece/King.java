package chess.domain.piece;

public class King extends Piece {

    public King(Color color) {
        super(color);
        this.type = Type.KING;
    }
}
