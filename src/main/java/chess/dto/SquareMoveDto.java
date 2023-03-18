package chess.dto;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class SquareMoveDto {

    private final Square current;
    private final Square destination;

    private SquareMoveDto(final Square current, final Square destination) {
        this.current = current;
        this.destination = destination;
    }

    // TODO: 검증 필요
    public static SquareMoveDto from(final String currentInput, final String destinationInput) {
        final Square current = getSquare(currentInput);
        final Square destination = getSquare(destinationInput);
        return new SquareMoveDto(current, destination);
    }

    private static Square getSquare(final String input) {
        final File file = File.from(input.charAt(0));
        final Rank rank = Rank.from(input.charAt(1));
        return Square.of(file, rank);
    }

    public Square getCurrent() {
        return current;
    }

    public Square getDestination() {
        return destination;
    }
}
