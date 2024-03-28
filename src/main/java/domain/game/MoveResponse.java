package domain.game;

public abstract class MoveResponse {
    public abstract boolean hasCaught();

    public abstract PieceType caughtPieceType();
}
