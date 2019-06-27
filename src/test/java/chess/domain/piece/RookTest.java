package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = Rook.whiteCreate();
    }

    @Test
    void 룩_이동_위_오른쪽2() {
        Vectors movableSquares = rook.movableArea(new Square(new Position(1), new Position(1)));
        Vectors expectedSquares = new Vectors(new HashSet<>());
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(2)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(3)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(4)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(5)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(6)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(7)), Direction.UP));
        expectedSquares.add(new Vector(new Square(new Position(1), new Position(8)), Direction.UP));

        expectedSquares.add(new Vector(new Square(new Position(2), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(3), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(4), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(5), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(6), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(7), new Position(1)), Direction.RIGHT));
        expectedSquares.add(new Vector(new Square(new Position(8), new Position(1)), Direction.RIGHT));

        assertThat(movableSquares).isEqualTo(expectedSquares);
    }

    @Test
    void 룩_이동_사방() {
        Vectors movableVectors = rook.movableArea(new Square(new Position(3), new Position(3)));
        Vectors expectedVectors = new Vectors(new HashSet<>());
        //위
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(4)), Direction.UP));
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(5)), Direction.UP));
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(6)), Direction.UP));
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(7)), Direction.UP));
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(8)), Direction.UP));

        //아래
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(1)), Direction.DOWN));
        expectedVectors.add(new Vector(new Square(new Position(3), new Position(2)), Direction.DOWN));

        //왼쪽
        expectedVectors.add(new Vector(new Square(new Position(1), new Position(3)), Direction.LEFT));
        expectedVectors.add(new Vector(new Square(new Position(2), new Position(3)), Direction.LEFT));


        //오른쪽
        expectedVectors.add(new Vector(new Square(new Position(4), new Position(3)), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(5), new Position(3)), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(6), new Position(3)), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(7), new Position(3)), Direction.RIGHT));
        expectedVectors.add(new Vector(new Square(new Position(8), new Position(3)), Direction.RIGHT));

        assertThat(movableVectors).isEqualTo(expectedVectors);
    }
}
