package chess.controller;

import java.util.List;

@FunctionalInterface
public interface ChessGameAction {

    void execute(List<String> commands);
}
