package domain.game;

public enum Side {
    BLACK,
    WHITE,
    NEUTRAL;

    public boolean isOpponentWith(Side side) {
        if (this.equals(NEUTRAL)) {
            throw new UnsupportedOperationException("서버 내부 에러 - Neutral side는 상대편을 확인할 수 없습니다.");
        }
        return !this.equals(side);
    }
}
