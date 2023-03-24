package controller.game;

import java.util.List;

@FunctionalInterface
public interface Action {
    void execute(long gameId, List<String> inputs);
}
