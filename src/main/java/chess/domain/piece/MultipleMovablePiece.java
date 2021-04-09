package chess.domain.piece;

public abstract class MultipleMovablePiece extends GeneralPiece {
    public MultipleMovablePiece(Team team, String initialName) {
        super(team, initialName);
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    @Override
    public final boolean multipleMovable() {
        return true;
    }
}
