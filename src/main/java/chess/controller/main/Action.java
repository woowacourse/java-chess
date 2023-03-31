package chess.controller.main;

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
