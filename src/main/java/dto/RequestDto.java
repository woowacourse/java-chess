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
        if (movePositionDto.doesNotHavePosition()) {
            throw new IllegalArgumentException("이동 위치 정보가 없습니다.");
        }
        return movePositionDto.source();
    }

    public Position destination() {
        if (movePositionDto.doesNotHavePosition()) {
            throw new IllegalArgumentException("이동 위치 정보가 없습니다.");
        }
        return movePositionDto.destination();
    }
}
