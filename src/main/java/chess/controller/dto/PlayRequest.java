package chess.controller.dto;

import java.util.List;

public class PlayRequest {

    private final String command;
    private final List<String> movePositions;

    public PlayRequest(final String command, final List<String> movePositions) {
        this.command = command;
        this.movePositions = movePositions;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getMovePositions() {
        return movePositions;
    }
}
