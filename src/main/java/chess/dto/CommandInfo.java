package chess.dto;

import chess.view.Command;

import java.util.Optional;

public record CommandInfo(
        Command command,
        Optional<String> source,
        Optional<String> target) {
    public static CommandInfo fromNonMovable(final Command command) {
        return new CommandInfo(command, Optional.empty(), Optional.empty());
    }

    public static CommandInfo ofMovable(final Command command, final String source, final String target) {
        return new CommandInfo(command, Optional.of(source), Optional.of(target));
    }
}
