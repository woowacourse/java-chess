package chess.view.dto;

public class SquareDto {

    private final char file;
    private final char rank;

    private SquareDto(final char file, final char rank) {
        this.file = file;
        this.rank = rank;
    }

    public static SquareDto of(final String square) {
        if (square == null || square.length() != 2) {
            throw new IllegalArgumentException("좌표값은 2글자만 가능합니다.");
        }
        char file = square.charAt(0);
        char rank = square.charAt(1);
        return new SquareDto(file, rank);
    }

    public char getFile() {
        return file;
    }

    public char getRank() {
        return rank;
    }
}
