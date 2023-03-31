package chess.controller.command;

import static chess.controller.command.Command.MOVE;

import chess.domain.model.position.Position;
import java.util.List;

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

    public Position getSourcePosition() {
        validateMove();
        String sourceInput = args.get(0);
        return Position.from(sourceInput);
    }

    public Position getTargetPosition() {
        validateMove();
        String targetInput = args.get(0);
        return Position.from(targetInput);
    }

    private void validateMove() {
        if (command != MOVE) {
            throw new IllegalStateException("MOVE 커맨드가 아닙니다.");
        }
    }

}
