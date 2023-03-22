package chess.view;

import java.util.Arrays;

public enum ChessEvent {

    START("start"),
    END("end");

    private final String value;

    ChessEvent(String value) {
        this.value = value;
    }

    public static ChessEvent of(String command) {
        return Arrays.stream(values())
                .filter(gameStartCommand -> gameStartCommand.value.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] start를 입력해야 게임이 시작됩니다."));
    }
}
