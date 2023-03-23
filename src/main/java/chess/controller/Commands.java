package chess.controller;

import java.util.List;

import static chess.controller.Command.MOVE;

public class Commands {

    private final Command command;
    private final List<String> args;

    public Commands(List<String> inputs){
        this.command = Command.getCommand(inputs);
        validate(inputs);
        inputs.remove(0);
        this.args = inputs;
    }

    private void validate(List<String> inputs) {
        if (command == MOVE && inputs.size() != 3) {
            throw new IllegalArgumentException("move a2 a3 형식으로 입력하세요.");
        }
    }

    public Command getCommand() {
        return command;
    }

    public boolean isNotEnd() {
        return command.isNotEnd();
    }

    public List<String> getArgs() {
        return args;
    }
}
