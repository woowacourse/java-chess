package domain.command;

import domain.Position;
import java.util.Arrays;
import java.util.List;

public class MoveCommand implements Command {
    private final Position from;
    private final Position to;

    public MoveCommand(String... options) {
        List<Position> inputOptions = Arrays.stream(options)
                .map(String::toUpperCase)
                .map(Position::valueOf)
                .toList();
        from = inputOptions.get(0);
        to = inputOptions.get(1);
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
