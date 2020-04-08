package chess.controller.dto;

import chess.service.Command;

import java.util.List;

public class RequestDto {
    private final Command command;
    private List<String> parameter;
    private long id;

    public RequestDto(Command command, List<String> parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    public RequestDto(final Command command, final List<String> parameter, final long id) {
        this.command = command;
        this.parameter = parameter;
        this.id = id;
    }

    public RequestDto(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public long getId() {
        return id;
    }
}
