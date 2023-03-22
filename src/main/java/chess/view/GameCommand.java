package chess.view;

public enum GameCommand {

    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public void validateCommand(String command) {
        if (this.command.equals(command)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 게임을 진행하기 위해서는 " + this.command + "를 입력해주세요.");
    }

    public String getCommand() {
        return command;
    }
}
