package chess.controller;

import java.util.List;

@FunctionalInterface
public interface Action {
    void execute(List<String> list);
}
