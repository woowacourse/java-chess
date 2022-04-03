package chess.controller.dto.request;

import chess.command.Command;
import chess.command.CommandFactory;
import java.util.List;

public class MoveRequest {

    private final String start;
    private final String target;

    public MoveRequest(String start, String target) {
        this.start = start;
        this.target = target;
    }

    public String getStart() {
        return start;
    }

    public String getTarget() {
        return target;
    }

    public Command toCommand() {
        return CommandFactory.create("move", List.of(start, target));
    }
}
