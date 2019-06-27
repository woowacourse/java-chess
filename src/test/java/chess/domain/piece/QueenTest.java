package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    Piece queen;

    @BeforeEach
    void setUp() {
        queen = Queen.whiteCreate();
    }

    @Test
    void movablelist() {
        Vectors movableList = queen.movableArea(new Square(new Position(6), new Position(6)));
        Vectors expected = new Vectors(new HashSet<>());

        expected.add(new Vector(new Square(new Position(7), new Position(7)), Direction.UP_RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(8)), Direction.UP_RIGHT));

        expected.add(new Vector(new Square(new Position(7), new Position(5)), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(4)), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new Position(5), new Position(5)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(4)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(3), new Position(3)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(2), new Position(2)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(1), new Position(1)), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new Position(5), new Position(7)), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(8)), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new Position(6), new Position(7)), Direction.UP));
        expected.add(new Vector(new Square(new Position(6), new Position(8)), Direction.UP));

        expected.add(new Vector(new Square(new Position(7), new Position(6)), Direction.RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(6)), Direction.RIGHT));

        expected.add(new Vector(new Square(new Position(6), new Position(5)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(4)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(3)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(2)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(1)), Direction.DOWN));

        expected.add(new Vector(new Square(new Position(1), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(2), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(3), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(5), new Position(6)), Direction.LEFT));

        assertThat(movableList).isEqualTo(expected);
    }
}
