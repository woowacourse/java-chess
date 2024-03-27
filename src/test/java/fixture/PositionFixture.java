package fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public final class PositionFixture {
    public static Position c1() {
        return new Position(new File('c'), new Rank(1));
    }

    public static Position c2() {
        return new Position(new File('c'), new Rank(2));
    }

    public static Position c3() {
        return new Position(new File('c'), new Rank(3));
    }

    public static Position c4() {
        return new Position(new File('c'), new Rank(4));
    }

    public static Position b1() {
        return new Position(new File('b'), new Rank(1));
    }

    public static Position b2() {
        return new Position(new File('b'), new Rank(2));
    }

    public static Position b3() {
        return new Position(new File('b'), new Rank(3));
    }

    public static Position b4() {
        return new Position(new File('b'), new Rank(4));
    }

    public static Position b5() {
        return new Position(new File('b'), new Rank(5));
    }

    public static Position b6() {
        return new Position(new File('b'), new Rank(6));
    }

    public static Position b7() {
        return new Position(new File('b'), new Rank(7));
    }

    public static Position c5() {
        return new Position(new File('c'), new Rank(5));
    }

    public static Position c7() {
        return new Position(new File('c'), new Rank(7));
    }

    public static Position d1() {
        return new Position(new File('d'), new Rank(1));
    }

    public static Position d2() {
        return new Position(new File('d'), new Rank(2));
    }

    public static Position d3() {
        return new Position(new File('d'), new Rank(3));
    }

    public static Position d5() {
        return new Position(new File('d'), new Rank(5));
    }

    public static Position F1() {
        return new Position(new File('f'), new Rank(1));
    }
}
