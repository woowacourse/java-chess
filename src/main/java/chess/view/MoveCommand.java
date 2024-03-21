package chess.view;

import chess.domain.position.Position;
import java.util.List;

public class MoveCommand {
    private static final int START_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    private final Position start;
    private final Position destination;

    private MoveCommand(Position start, Position destination) {
        this.start = start;
        this.destination = destination;
    }

    public static MoveCommand of(String input) {
        List<String> command = List.of(input.split(" "));
        String start = command.get(START_INDEX);
        String destination = command.get(DESTINATION_INDEX);
        return new MoveCommand(makePosition(start), makePosition(destination));
    }

    private static Position makePosition(String input) {
        int rowNumber = 8 - (input.charAt(1) - '0');
        int columnNumber = input.charAt(0) - 'a';
        return Position.of(rowNumber, columnNumber);
    }

    public Position getStart() {
        return start;
    }

    public Position getDestination() {
        return destination;
    }
}
