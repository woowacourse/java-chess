package dto;

import domain.GameCommand;
import domain.position.Position;

import java.util.Optional;

public record RequestDto(GameCommand command, Optional<Position> source, Optional<Position> destination) {

    public static RequestDto of(GameCommand command) {
        return new RequestDto(command, Optional.empty(), Optional.empty());
    }

    public static RequestDto of(GameCommand command, Position source, Position destination) {
        return new RequestDto(command, Optional.of(source), Optional.of(destination));
    }
}
