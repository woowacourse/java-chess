package chess.domain.position;

public class Coordinate {

    private final File file;
    private final Rank rank;

    private Coordinate(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Coordinate of(String coordinate) {
        validateLength(coordinate);
        validateFileChar(coordinate.charAt(0));
        validateRankChar(coordinate.charAt(1));

        return new Coordinate(
                File.of(coordinate.charAt(0)),
                Rank.of(coordinate.charAt(1)));
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
        return file;
    }

    public Rank rank() {
        return rank;
    }
}
