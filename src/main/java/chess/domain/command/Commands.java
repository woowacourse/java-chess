package chess.domain.command;


import java.util.Arrays;

public enum Commands {

    START("start", new Start()),
    MOVE("move", new Move()),
    STATUS("status", new Status()),
    END("end", new End());

    public static final String WRONG_COMMAND = "잘못된 메세지입니다.";

    private final String receivedCommand;
    private final Command command;

    Commands(final String receivedCommand, Command command) {
        this.receivedCommand = receivedCommand;
        this.command = command;
    }

    public static Command matchCommand(final String input) {
        String processedInput = input.split(" ")[0];
        Commands commands = Arrays.stream(values())
            .filter(element -> element.receivedCommand.equals(processedInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND));
        return commands.command;
    }
}
