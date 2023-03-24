package controller.room;

import java.util.List;

@FunctionalInterface
public interface GameRoomAction {
    void execute(List<String> inputs);
}
