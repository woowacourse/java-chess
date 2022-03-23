package chess.domain;

public class GameCommand {

    private final String command;

    public GameCommand(String rawCommand) {
        validateCommand(rawCommand);
        this.command = rawCommand;
    }

    private void validateCommand(String command) {
        if (command.equals("start") || command.equals("end")) {
            return;
        }
        throw new IllegalArgumentException("적절한 명령어가 아닙니다.");
    }

    public boolean isStart() {
        return command.equals("start");
    }

    public boolean isEnd() {
        return command.equals("end");
    }
}
