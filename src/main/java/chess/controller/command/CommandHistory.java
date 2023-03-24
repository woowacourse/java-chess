package chess.controller.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private final Deque<Command> history = new ArrayDeque<>();

    public void addHistory(Command command) {
        history.addFirst(command);
    }

    public Deque<Command> getHistory() {
        return history;
    }
}
