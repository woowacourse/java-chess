package dto;

import domain.GameCommand;
import domain.position.Position;

public record RequestDto(GameCommand command, MovePositionDto movePositionDto) {
    public static RequestDto of(GameCommand command) {
        return new RequestDto(command, MovePositionDto.noPosition());
    }

    public static RequestDto of(GameCommand command, Position source, Position destination) {
        return new RequestDto(command, MovePositionDto.of(source, destination));
    }

    public Position source() {
        return movePositionDto.source();
    }

    public Position destination() {
        return movePositionDto.destination();
    }
}
