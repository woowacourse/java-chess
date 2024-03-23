package chess.view.command;

import java.util.List;

import chess.domain.piece.attribute.Position;

public class MoveCommand {

    private final Position source;
    private final Position target;

    public MoveCommand(final String input) {
        List<String> command = List.of(input.split(" "));
        this.source = Position.from(command.get(1));
        this.target = Position.from(command.get(2));
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
