package dto;

import domain.GameCommand;
import domain.game.GameRequest;
import domain.position.Position;
import java.util.List;

public record CommandDto(GameCommand command, MovePositionDto movePositionDto) {
    private static final String NO_POSITION_DATA = "이동 위치 정보가 없습니다.";

    public static CommandDto of(GameCommand command) {
        return new CommandDto(command, MovePositionDto.noPosition());
    }

    public static CommandDto of(GameCommand command, Position source, Position destination) {
        return new CommandDto(command, MovePositionDto.of(source, destination));
    }

    public Position source() {
        if (movePositionDto.doesNotHavePosition()) {
            throw new IllegalArgumentException(NO_POSITION_DATA);
        }
        return movePositionDto.source();
    }

    public Position destination() {
        if (movePositionDto.doesNotHavePosition()) {
            throw new IllegalArgumentException(NO_POSITION_DATA);
        }
        return movePositionDto.destination();
    }

    public GameRequest asRequest() {
        if (movePositionDto.doesNotHavePosition()) {
            return GameRequest.ofNoArgument(command);
        }
        return new GameRequest(command, List.of(source(), destination()));
    }
}
