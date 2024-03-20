package domain.game;

import domain.chessboard.Square;
import domain.position.Position;

public final class SquareFixture {
    public static Square generateSquare(final Position position) {
        return new Square(position);

    }
}
