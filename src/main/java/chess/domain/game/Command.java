package chess.domain.game;

public enum Command {
    START,
    END,
    MOVE,
    STATUS;

    public static Command of(String commandValue) {
        try {
            return valueOf(commandValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }
}
