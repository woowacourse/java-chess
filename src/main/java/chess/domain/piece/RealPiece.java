package chess.domain.piece;

public class RealPiece extends Piece{
    private final Color color;

    public RealPiece(Color color, String notation) {
        super(notation);
        this.color = color;
    }

    @Override
    public String getNotation() {
        return color.changeNotation(super.getNotation());
    }
}
