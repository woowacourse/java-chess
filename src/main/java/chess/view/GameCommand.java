package chess.view;

import java.util.Arrays;

public enum GameCommand {
    START("start", "게임 시작 : start"),
    END("end", "게임 종료 : end"),
    MOVE("move", "게임 이동 : move source위치 target위치 - 예. move b2 b3"),
    ;

    private final String consoleCommand;
    private final String helperMassage;

    GameCommand(final String consoleCommand, final String helperMassage) {
        this.consoleCommand = consoleCommand;
        this.helperMassage = helperMassage;
    }

    public static GameCommand getGameCommand(final String input) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.consoleCommand.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어 입니다."));
    }

    public String getHelperMessage() {
        return helperMassage;
    }
}
