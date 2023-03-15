package chessgame;

import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class PointFixture {
    public static final Point A1 = Point.of(File.A, Rank.ONE);
    public static final Point A2 = Point.of(File.A, Rank.TWO);
    public static final Point A3 = Point.of(File.A, Rank.THREE);
    public static final Point A4 = Point.of(File.A, Rank.FOUR);
    public static final Point A8 = Point.of(File.A, Rank.EIGHT);
    public static final Point B1 = Point.of(File.B, Rank.ONE);
    public static final Point B3 = Point.of(File.B, Rank.THREE);
    public static final Point F1 = Point.of(File.F, Rank.ONE);
    public static final Point F5 = Point.of(File.F, Rank.FIVE);

    public static final Point F7 = Point.of(File.F, Rank.SEVEN);
    public static final Point F8 = Point.of(File.F, Rank.EIGHT);
    public static final Point H1 = Point.of(File.H, Rank.ONE);
    public static final Point H8 = Point.of(File.H, Rank.EIGHT);

}
