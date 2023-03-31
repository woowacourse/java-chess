package chess.dto;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class SquareMoveDto {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Square current;
    private final Square destination;

    private SquareMoveDto(final Square current, final Square destination) {
        this.current = current;
        this.destination = destination;
    }

    public static SquareMoveDto from(final String currentInput, final String destinationInput) {
        final Square current = getSquare(currentInput);
        final Square destination = getSquare(destinationInput);
        return new SquareMoveDto(current, destination);
    }

    private static Square getSquare(final String input) {
        final File file = File.from(input.charAt(FILE_INDEX));
        final Rank rank = Rank.from(input.charAt(RANK_INDEX));
        return Square.of(file, rank);
    }

    public Square getCurrent() {
        return current;
    }

    public Square getDestination() {
        return destination;
    }
}
