package chess.controller;

import java.util.List;

@FunctionalInterface
public interface Action {

    void execute(final List<String> list);

}
