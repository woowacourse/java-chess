package chess.controller;

import java.util.List;

@FunctionalInterface
public interface ChessGameAction {
    ChessGameAction EMPTY = ignore -> {
    };

    void execute(final List<String> commands);
}
