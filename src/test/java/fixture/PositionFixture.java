package fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public final class PositionFixture {
    public static Position createC1() {
        return new Position(new File('c'), new Rank(1));
    }

    public static Position createC2() {
        return new Position(new File('c'), new Rank(2));
    }

    public static Position createC3() {
        return new Position(new File('c'), new Rank(3));
    }

    public static Position createB1() {
        return new Position(new File('b'), new Rank(1));
    }

    public static Position createB2() {
        return new Position(new File('b'), new Rank(2));
    }

    public static Position createB7() {
        return new Position(new File('b'), new Rank(7));
    }


}
