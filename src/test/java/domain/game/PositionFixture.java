package domain.game;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public final class PositionFixture {
    public static Position generateSourcePosition() {
        return new Position(new File('b'), new Rank(1));
    }

    public static Position generateTargetPosition() {
        return new Position(new File('c'), new Rank(2));
    }
}
