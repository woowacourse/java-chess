package chess.controller;

import java.util.List;

import static chess.controller.Command.MOVE;

public class Commands {

    private final Command command;
    private final List<String> args;

    public Commands(List<String> inputs){
        this.command = Command.getCommand(inputs);
        inputs.remove(0);
        this.args = inputs;
    }

    public Command getCommand() {
        return command;
    }

    public boolean isNotEnd() {
        return command.isNotEnd();
    }

    public String getMovablePiece() {
        validateMove();
        return args.get(0);
    }

    public String getTargetPosition() {
        validateMove();
        return args.get(1);
    }

    private void validateMove() {
        if (command != MOVE) {
            throw new IllegalStateException("MOVE 커맨드가 아닙니다.");
        }
    }

}
