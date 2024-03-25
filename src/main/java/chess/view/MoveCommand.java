package chess.view;

import chess.domain.position.Position;
import chess.domain.position.RowPosition;

import java.util.List;

public class MoveCommand {
    private static final int RANK_BASE_NUMBER = RowPosition.MAX_NUMBER + 1;
    private static final int FILE_BASE_NUMBER = 'a';
    private static final String COMMAND_DELIMITER = " ";
    private static final int START_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    private final Position start;
    private final Position destination;

    private MoveCommand(Position start, Position destination) {
        this.start = start;
        this.destination = destination;
    }

    public static MoveCommand of(String input) {
        List<String> command = List.of(input.split(COMMAND_DELIMITER));
        String start = command.get(START_INDEX);
        String destination = command.get(DESTINATION_INDEX);
        return new MoveCommand(makePosition(start), makePosition(destination));
    }

    private static Position makePosition(String input) {
        int rowNumber = RANK_BASE_NUMBER - Character.getNumericValue(input.charAt(ROW_INDEX));
        int columnNumber = input.charAt(COLUMN_INDEX) - FILE_BASE_NUMBER;
        return Position.of(rowNumber, columnNumber);
    }

    public Position getStart() {
        return start;
    }

    public Position getDestination() {
        return destination;
    }
}
