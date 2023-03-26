package chess.controller;

import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(int gameId, List<String> arguments);
}
