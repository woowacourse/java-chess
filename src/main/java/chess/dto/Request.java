package chess.dto;

import chess.controller.Command;

public class Request {

    private final Command command;
    private final String input;

    private Request(String input, Command command) {
        this.command = command;
        this.input = input;
    }

    public static Request toStart(String input) {
        return new Request(input, Command.startEnd(input));
    }

    public static Request toPlay(String input) {
        return new Request(input, Command.MoveStatusEnd(input));
    }

    public boolean isEnd() {
        return command.isEnd();
    }

    public boolean isStatus() {
        return command.isStatus();
    }

    public String getSource() {
        return command.getSource(input);
    }

    public String getTarget() {
        return command.getTarget(input);
    }
}
