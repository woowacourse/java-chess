package chess.controller;

import java.util.List;

@FunctionalInterface
public interface ChessGameAction {

    ChessGameAction EMPTY = ignore -> {
    };

    void execute(List<String> commands);
}
