package chess.domain.chessGame;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum TurnState {
    WAIT(Color.EMPTY, false),
    WHITE(Color.WHITE, true),
    WHITE_INACTIVE(Color.WHITE, false),
    BLACK(Color.BLACK, true),
    BLACK_INACTIVE(Color.BLACK, false),
    END(Color.EMPTY, false);

    private final Color color;
    private final boolean isActive;

    TurnState(Color color, boolean isActive) {
        this.color = color;
        this.isActive = isActive;
    }

    public static TurnState inactivate(TurnState turnState) {
        return Arrays.stream(values())
                .filter(value -> !value.isActive && value.color == turnState.color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상태 설정입니다"));
    }

    public static TurnState activate(TurnState turnState) {
        return Arrays.stream(values())
                .filter(value -> value.isActive && value.color == turnState.color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상태 설정입니다"));
    }

    public static TurnState of(String stateName) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(stateName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상태입니다"));
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Color color() {
        return this.color;
    }
}
