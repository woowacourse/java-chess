package chess.controller;

import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(List<String> arguments);
}
