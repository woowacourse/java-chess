package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    Piece king;

    @BeforeEach
    void setUp() {
        king = King.whiteCreate();
    }

    @Test
    void 이동() {
        Vectors movableVectors = king.movableArea(new Square(new Position(3), new Position(3)));
        Vectors expectedVectors = new Vectors(new HashSet<>());
        //위
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(4)), Direction.UP));

        //아래
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(2)), Direction.DOWN));

        //왼쪽
        expectedVectors.add(new Vector(new Square(new Position(2), new Position(3)), Direction.LEFT));

        //오른쪽
        expectedVectors.add(new Vector(new Square(new Position(4), new Position(3)), Direction.RIGHT));

        expectedVectors.add(new Vector(new Square(new Position(4), new Position(4)), Direction.UP_RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(4), new Position(2)), Direction.DOWN_RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(2), new Position(2)), Direction.DOWN_LEFT));
        expectedVectors.add(new Vector(new Square(new Position(2), new Position(4)), Direction.UP_LEFT));

        assertThat(movableVectors).isEqualTo(expectedVectors);
    }

    @Test
    void 이동_구석() {
        Vectors movableVectors = king.movableArea(new Square(new Position(1), new Position(1)));
        Vectors expectedVectors = new Vectors(new HashSet<>());
        //위
        expectedVectors.add(new Vector(new Square(new Position(1), new Position(2)), Direction.UP));

        //오른쪽
        expectedVectors.add(new Vector(new Square(new Position(2), new Position(1)), Direction.RIGHT));

        expectedVectors.add(new Vector(new Square(new Position(2), new Position(2)), Direction.UP_RIGHT));

        assertThat(movableVectors).isEqualTo(expectedVectors);
    }
}
