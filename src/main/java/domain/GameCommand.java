package domain;

public enum GameCommand {

    START,
    MOVE,
    END;

    public static GameCommand of(String inputCommand) {
        try {
            return GameCommand.valueOf(inputCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 명령어를 입력했습니다.");
        }
    }
}
