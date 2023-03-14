package chess;

public class GameCommand {
    private final String gameCommand;

    public GameCommand(final String gameCommand) {
        validateGameCommand(gameCommand);
        this.gameCommand = gameCommand;
    }

    private void validateGameCommand(final String gameCommand) {
        if (!(gameCommand.equals("start") || gameCommand.equals("end"))) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }
    }
}
