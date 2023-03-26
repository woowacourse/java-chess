package chess.controller.room;

import java.util.List;

@FunctionalInterface
public interface RoomAction {
    RoomAction EMPTY = ignore -> {
    };

    void execute(final List<String> commands);
}
