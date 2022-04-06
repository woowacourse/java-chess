package chess.console.dto;

import chess.console.controller.Command;
import java.util.List;

public class CommandRequest {

    private static final String DEFAULT_POSITION = "";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int DEFAULT_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final Command command;
    private final String source;
    private final String target;

    private CommandRequest(Command command) {
        this(command, DEFAULT_POSITION, DEFAULT_POSITION);
    }

    private CommandRequest(Command command, String source, String target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public static CommandRequest of(List<String> inputs) {
        validateLength(inputs);
        if (inputs.size() == 1) {
            return new CommandRequest(Command.from(inputs.get(COMMAND_INDEX)));
        }
        return new CommandRequest(Command.from(inputs.get(COMMAND_INDEX)), inputs.get(SOURCE_INDEX), inputs.get(
                TARGET_INDEX));
    }

    private static void validateLength(List<String> inputs) {
        if(!(inputs.size() == DEFAULT_COMMAND_SIZE || inputs.size() == MOVE_COMMAND_SIZE)) {
            throw new IllegalArgumentException("명령어의 형식이 잘못되었습니다.");
        }
    }

    public Command getCommand() {
        return command;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
