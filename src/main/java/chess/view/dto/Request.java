package chess.view.dto;

import chess.view.Command;

public class Request {

    private final Command command;
    private final MoveRequest moveRequest;

    private Request(Command command, MoveRequest moveRequest) {
        this.command = command;
        this.moveRequest = moveRequest;
    }

    public static Request createSingleCommand(Command command) {
        return new Request(command, null);
    }

    public static Request createMoveCommand(String source, String target) {
        return new Request(Command.MOVE, MoveRequest.of(source, target));
    }

    public Command getCommand() {
        return command;
    }

    public MoveRequest getMoveRequest() {
        return moveRequest;
    }
}
