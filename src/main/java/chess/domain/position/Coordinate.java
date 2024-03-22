package chess.domain.position;

public class Coordinate {

    private final char fileChar;
    private final char rankChar;

    private Coordinate(char fileChar, char rankChar) {
        this.fileChar = fileChar;
        this.rankChar = rankChar;
    }

    public static Coordinate of(String coordinate) {
        validateLength(coordinate);
        validateFileChar(coordinate.charAt(0));
        validateRankChar(coordinate.charAt(1));

        return new Coordinate(coordinate.charAt(0), coordinate.charAt(1));
    }

    private static void validateLength(String coordinate) {
        if (coordinate.length() != 2) {
            throw new IllegalArgumentException("입력 좌표 길이가 잘못되었습니다");
        }
    }

    private static void validateFileChar(char fileChar) {
        File.of(fileChar);
    }

    private static void validateRankChar(char rankChar) {
        Rank.of(rankChar);
    }

    public File file() {
        return File.of(fileChar);
    }

    public Rank rank() {
        return Rank.of(rankChar);
    }
}
