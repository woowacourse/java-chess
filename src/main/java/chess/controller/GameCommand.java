package chess.controller;

public enum GameCommand {
    MOVE,
    END;

    public static GameCommand of(String input) {
        if ("move".equalsIgnoreCase(input)) {
            return MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }
}
