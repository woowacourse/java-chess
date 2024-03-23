package fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public final class PositionFixture {
    public static Position generateC1Position() {
        return new Position(new File('c'), new Rank(1));
    }

    public static Position generateC2Position() {
        return new Position(new File('c'), new Rank(2));
    }

    public static Position generateC3Position() {
        return new Position(new File('c'), new Rank(3));
    }

    public static Position generateC4Position() {
        return new Position(new File('c'), new Rank(4));
    }

    public static Position generateB1Position() {
        return new Position(new File('b'), new Rank(1));
    }

    public static Position generateB2Position() {
        return new Position(new File('b'), new Rank(2));
    }

    public static Position generateB3Position() {
        return new Position(new File('b'), new Rank(3));
    }

    public static Position generateB7Position() {
        return new Position(new File('b'), new Rank(7));
    }

    public static Position generateF1Position() {
        return new Position(new File('f'), new Rank(1));
    }
}
