package fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public final class PositionFixture {

    public static Position createA1() {
        return new Position(new File('a'), new Rank(1));
    }

    public static Position createA2() {
        return new Position(new File('a'), new Rank(2));
    }

    public static Position createA3() {
        return new Position(new File('a'), new Rank(3));
    }

    public static Position createC1() {
        return new Position(new File('c'), new Rank(1));
    }

    public static Position createC2() {
        return new Position(new File('c'), new Rank(2));
    }

    public static Position createC3() {
        return new Position(new File('c'), new Rank(3));
    }

    public static Position createC5() {
        return new Position(new File('c'), new Rank(5));
    }

    public static Position createC7() {
        return new Position(new File('c'), new Rank(7));
    }

    public static Position createB1() {
        return new Position(new File('b'), new Rank(1));
    }

    public static Position createB2() {
        return new Position(new File('b'), new Rank(2));
    }

    public static Position createB3() {
        return new Position(new File('b'), new Rank(3));
    }

    public static Position createB4() {
        return new Position(new File('b'), new Rank(4));
    }

    public static Position createB5() {
        return new Position(new File('b'), new Rank(5));
    }

    public static Position createB7() {
        return new Position(new File('b'), new Rank(7));
    }


}
