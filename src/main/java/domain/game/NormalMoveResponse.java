package domain.game;

public final class NormalMoveResponse extends MoveResponse {
    @Override
    public boolean hasCaught() {
        return false;
    }

    @Override
    public PieceType caughtPieceType() {
        throw new IllegalStateException("상대의 말이 잡히지 않았습니다.");
    }
}
