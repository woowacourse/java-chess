package chess.controller;

import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(final List<String> commands);
}
