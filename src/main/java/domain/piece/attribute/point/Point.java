package domain.piece.attribute.point;

import util.DirectionUtil;

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

    public static Point fromIndex(final Index index) {
        if (!index.isInBoundary()) {
            throw new IllegalArgumentException("파일과 랭크의 범위를 벗어났습니다.");
        }
        return new Point(File.findByIndex(index.horizontal()), Rank.findByIndex(index.vertical()));
    }

    public Direction calculate(final Point point) {
        return DirectionUtil.determineDirection(this, point);
    }


    public static Point from(final String value) {
        validate(value);
        File file = File.from(value.charAt(0));
        Rank rank = Rank.from(Integer.parseInt(value.substring(1)));
        return new Point(file, rank);
    }

    private static void validate(final String value) {
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("파일은 a~h이고, 랭크는 아래부터 위로 1~8까지입니다.");
        }
    }

    public Index toIndex() {
        return new Index(rank.ordinal(), file.ordinal());
    }
}
