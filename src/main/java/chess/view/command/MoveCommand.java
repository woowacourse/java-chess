package chess.view.command;

import chess.domain.attribute.Square;
import java.util.List;

public class MoveCommand {

    private final Square source;
    private final Square target;

    public MoveCommand(final String input) {
        List<String> command = List.of(input.split(" "));
        this.source = Square.of(command.get(1));
        this.target = Square.of(command.get(2));
    }

    public Square source() {
        return source;
    }

    public Square target() {
        return target;
    }
}
