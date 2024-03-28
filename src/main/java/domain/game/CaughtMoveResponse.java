package domain.game;

public final class CaughtMoveResponse extends MoveResponse {
    private final PieceType caughtPieceType;

    public CaughtMoveResponse(PieceType caughtPieceType) {
        this.caughtPieceType = caughtPieceType;
    }

    @Override
    public boolean hasCaught() {
        return true;
    }

    @Override
    public PieceType caughtPieceType() {
        return caughtPieceType;
    }
}
