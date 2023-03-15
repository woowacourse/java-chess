package chess.domain.piece;

public class PositionConverter {
    private static final char FIRST_FILE = 'a';
    private static final char FIRST_RANK = '1';

    public Position convert(final String position) {
        final int file = position.charAt(0);
        final int row = position.charAt(1);
        return new Position(file - FIRST_FILE, row - FIRST_RANK);
    }
}
