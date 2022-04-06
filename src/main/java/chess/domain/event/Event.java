package chess.domain.event;

import chess.dto.request.MoveCommandDto;

public class Event {

    private final EventType type;
    private final String description;

    public Event(EventType type, String description) {
        this.type = type;
        this.description = description;
    }

    public MoveCommandDto toMoveCommand() {
        if (type != EventType.MOVE) {
            throw new UnsupportedOperationException("이동 이벤트가 아닙니다.");
        }
        return new MoveCommandDto(description);
    }
}
