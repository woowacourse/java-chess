package chess.controller;

import chess.view.request.Request;
import java.util.function.Consumer;

public class Action {

    private final Consumer<Request> action;

    public Action(Consumer<Request> action) {
        this.action = action;
    }

    public void execute(Request args) {
        action.accept(args);
    }
}
