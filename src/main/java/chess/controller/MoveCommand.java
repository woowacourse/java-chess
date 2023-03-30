package chess.controller;

import chess.domain.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveCommand {

    public static final int SOURCE_SQUARE_INDEX = 1;
    public static final int DESTINATION_SQUARE_INDEX = 2;
    private final Square source;
    private final Square destination;

    private MoveCommand(final Square source, final Square destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveCommand from(final String inputs) {
        List<String> splitInputs = splitInputs(inputs);
        validateEmpty(splitInputs);
        validateMove(splitInputs);
        return getMoveCommand(splitInputs);
    }

    private static List<String> splitInputs(String inputs) {
        return Arrays.stream(inputs.split(" "))
                .collect(Collectors.toList());
    }

    private static void validateEmpty(final List<String> inputs) {
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException("move를 입력하세요.");
        }
    }

    private static void validateMove(final List<String> inputs) {
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("(예. move b2 b3)와 같이 입력해야 합니다.");
        }
    }

    private static MoveCommand getMoveCommand(final List<String> inputs) {
        return new MoveCommand(Square.from(inputs.get(SOURCE_SQUARE_INDEX)), Square.from(inputs.get(DESTINATION_SQUARE_INDEX)));
    }

    public Square getSource() {
        return source;
    }

    public Square getDestination() {
        return destination;
    }
}
