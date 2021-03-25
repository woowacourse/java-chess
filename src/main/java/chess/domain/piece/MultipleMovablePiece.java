package chess.domain.piece;

public abstract class MultipleMovablePiece extends GeneralPiece {
    public MultipleMovablePiece(Team team, String initialName) {
        super(team, initialName);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean multipleMovable() {
        return true;
    }
}
