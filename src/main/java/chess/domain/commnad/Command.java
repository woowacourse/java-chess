package chess.domain.commnad;

import chess.exception.CommandMessage;
import java.util.Arrays;
import java.util.List;

public class Command {

    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final String STATUS_COMMAND = "status";
    private static final int COMMAND_INDEX = 0;
    private static final int SELECTED_PIECE = 1;
    private static final int DESTINATION = 2;

    private final List<String> commands;

    private Command(final List<String> commands) {
        validate(commands);
        this.commands = commands;
    }

    public static Command from(final String input) {
        List<String> commands = Arrays.asList(input.split(" "));
        return new Command(commands);
    }

    public void validate(final List<String> commands) {
        List<String> permittedCommands = List.of(START_COMMAND, MOVE_COMMAND, END_COMMAND, STATUS_COMMAND);

        if (!permittedCommands.contains(commands.get(COMMAND_INDEX))) {
            throw new IllegalArgumentException(CommandMessage.COMMAND_INVALID.getMessage());
        }
    }

    public boolean isGameStop() {
        return commands.get(COMMAND_INDEX).equals(END_COMMAND);
    }

    public boolean isMove() {
        return commands.get(COMMAND_INDEX).equals(MOVE_COMMAND);
    }

    public String findSelectedPiece() {
        return commands.get(SELECTED_PIECE);
    }

    public String findDestination() {
        return commands.get(DESTINATION);
    }

    public boolean isCreateNewGame() {
        return commands.get(COMMAND_INDEX).equals(START_COMMAND);
    }

    public boolean isStatus() {
        return commands.get(COMMAND_INDEX).equals(STATUS_COMMAND);
    }
}
