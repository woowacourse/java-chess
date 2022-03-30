package chess.domain.piece;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(final Team team, final double point) {
        super(team, point);
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
