package fixture;

import domain.File;
import domain.Position;
import domain.Rank;

public class PositionFixture {

    public static Position d4() {
        return new Position(File.D, Rank.FOUR);
    }

    public static Position A1() {
        return new Position(File.A, Rank.ONE);
    }

    public static Position A2() {
        return new Position(File.A, Rank.TWO);
    }

    public static Position A3() {
        return new Position(File.A, Rank.THREE);
    }

    public static Position a4() {
        return new Position(File.A, Rank.FOUR);
    }

    public static Position a7() {
        return new Position(File.A, Rank.SEVEN);
    }

    public static Position a8() {
        return new Position(File.A, Rank.EIGHT);
    }

    public static Position b1() {
        return new Position(File.B, Rank.ONE);
    }

    public static Position b2() {
        return new Position(File.B, Rank.TWO);
    }

    public static Position b3() {
        return new Position(File.B, Rank.THREE);
    }

    public static Position c1() {
        return new Position(File.C, Rank.ONE);
    }

    public static Position c2() {
        return new Position(File.C, Rank.TWO);
    }

    public static Position c3() {
        return new Position(File.C, Rank.THREE);
    }

    public static Position c4() {
        return new Position(File.C, Rank.FOUR);
    }

    public static Position a5() {
        return new Position(File.C, Rank.FIVE);
    }
}
