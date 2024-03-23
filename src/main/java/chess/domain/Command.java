package chess.domain;

import java.util.List;

public class Command {
    public static final String START_COMMAND = "start";
    public static final String END_COMMAND = "end";
    public static final String MOVE_COMMAND = "move";

    private final List<String> commands;

    public Command(final List<String> commands) {
        this.commands = List.copyOf(commands);
    }

    public boolean isEnd() {
        return commands.get(0).equals(END_COMMAND);
    }

    public String sourceSquare() {
        return commands.get(1);
    }

    public String targetSquare() {
        return commands.get(2);
    }
}
