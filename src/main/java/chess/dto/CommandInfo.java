package chess.dto;

import chess.domain.Command;
import chess.domain.position.Position;

import java.util.Optional;

public record CommandInfo(
        Command command,
        Optional<Position> source,
        Optional<Position> target) {
    public static CommandInfo fromNonMovable(final Command command) {
        return new CommandInfo(command, Optional.empty(), Optional.empty());
    }

    public static CommandInfo ofMovable(final Command command, final Position source, final Position target) {
        return new CommandInfo(command, Optional.of(source), Optional.of(target));
    }
}
