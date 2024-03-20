package domain.piece.point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Point(File file, Rank rank) {

    private static final Pattern pattern = Pattern.compile("[a-h][1-8]");

    public int getFileIndex() {
        return this.file.ordinal();
    }

    public int getRankIndex() {
        return this.rank.ordinal();
    }


    public File nextFile() {
        return file.next();
    }

    public File prevFile() {
        return file.prev();
    }

    public Rank nextRank() {
        return rank.next();
    }

    public Rank prevRank() {
        return rank.prev();
    }

    public static Point from(String value) {
        validate(value);
        File file = File.from(value.charAt(0));
        Rank rank = Rank.from(Integer.parseInt(value.substring(1)));
        return new Point(file, rank);
    }

    private static void validate(String value) {
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("파일은 a~h이고, 랭크는 아래부터 위로 1~8까지입니다.");
        }
    }
}
