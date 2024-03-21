package fixture;

import domain.game.Square;
import domain.position.Position;

public final class SquareFixture {
    public static Square generateSquare(final Position position) {
        return new Square(position);

    }
}
