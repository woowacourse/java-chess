package chess.controller;

import java.util.List;

@FunctionalInterface
public interface Action {
    Action EMPTY = ignore -> {
    };

    void execute(final List<String> commands);
}
