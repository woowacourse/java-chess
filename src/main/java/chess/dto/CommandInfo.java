package chess.dto;

import chess.view.Command;

import java.util.Optional;

public record CommandInfo(
        Command command,
        Optional<String> source,
        Optional<String> target) {
    public static CommandInfo fromNonMovable(Command command) {
        return new CommandInfo(command, Optional.empty(), Optional.empty());
    }

    public static CommandInfo ofMovable(Command command, String source, String target) {
        return new CommandInfo(command, Optional.of(source), Optional.of(target));
    }
}
