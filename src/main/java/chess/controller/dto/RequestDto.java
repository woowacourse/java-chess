package chess.controller.dto;

import java.util.List;

public class RequestDto {
    private final Command command;
    private List<String> parameter;

    public RequestDto(Command command, List<String> parameter) {
        this.command = command;
        this.parameter = parameter;
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
}
