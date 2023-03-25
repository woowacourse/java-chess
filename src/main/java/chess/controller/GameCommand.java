package chess.controller;

public enum GameCommand {
    START,
    MOVE,
    END,
    STATUS,
    EMPTY,
    CLEAR,
    ;

    public static GameCommand of(String input) {
        if ("start".equalsIgnoreCase(input)) {
            return START;
        }
        if ("move".equalsIgnoreCase(input)) {
            return MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return END;
        }
        if ("status".equalsIgnoreCase(input)) {
            return STATUS;
        }
        if ("clear".equalsIgnoreCase(input)) {
            return CLEAR;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }
}
