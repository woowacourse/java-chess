package chess.controller.dto;

import chess.controller.Command;

import java.util.Collections;
import java.util.List;

public class CommandDto {

    private final Command command;
    private final List<Integer> source;
    private final List<Integer> target;


    public CommandDto(final Command command, final List<Integer> source, final List<Integer> target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public CommandDto(final Command command) {
        this.command = command;
        this.source = Collections.emptyList();
        this.target = Collections.emptyList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Integer> getSource() {
        return source;
    }

    public List<Integer> getTarget() {
        return target;
    }
}
