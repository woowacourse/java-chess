package chess.controller;

import java.util.List;
import java.util.function.Consumer;

public final class Action {

    private final Consumer<List<String>> consumer;

    public Action(final Consumer<List<String>> consumer) {
        this.consumer = consumer;
    }

    public void excute(final List<String> commands) {
        consumer.accept(commands);
    }
}
