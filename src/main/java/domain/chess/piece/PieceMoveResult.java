package domain.chess.piece;

public enum PieceMoveResult {
    SUCCESS, FAILURE, CATCH;

    public boolean toBoolean() {
        return !this.equals(FAILURE);
    }
}
