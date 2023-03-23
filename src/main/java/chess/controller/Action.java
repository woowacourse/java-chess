package chess.controller;

import java.util.List;
import java.util.function.Consumer;

public class Action {

    private final Consumer<List<String>> action;

    public Action(Consumer<List<String>> action) {
        this.action = action;
    }

    public void execute(List<String> args) {
        action.accept(args);
    }
}
