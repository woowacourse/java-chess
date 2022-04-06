package chess.domain.game.statistics;

public enum GameState {

    WHITE_TURN("White Turn"),
    BLACK_TURN("Black Turn"),
    OVER("종료");

    private final String value;

    GameState(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
