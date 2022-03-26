package chess.dto;

import chess.controller.Command;
import java.util.List;

public class CommandRequest {

    private final Command command;
    private final String source;
    private final String target;

    private CommandRequest(Command command) {
        this(command, "", "");
    }

    private CommandRequest(Command command, String source, String target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public static CommandRequest of(List<String> inputs) {
        validateLength(inputs);
        if (inputs.size() == 1) {
            return new CommandRequest(Command.of(inputs.get(0)));
        }
        return new CommandRequest(Command.of(inputs.get(0)), inputs.get(1), inputs.get(2));
    }

    private static void validateLength(List<String> inputs) {
        if(!(inputs.size() == 1 || inputs.size() == 3)) {
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
