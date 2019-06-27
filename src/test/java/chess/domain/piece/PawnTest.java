package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.path.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {
    private Piece wPawn;
    private Piece bPawn;

    @BeforeEach
    void setUp() {
        wPawn = Pawn.whiteCreate();
        bPawn = Pawn.blackCreate();
    }

    @Test
    void 화이트_폰_이동가능영역() {
        Vectors movableVector = wPawn.movableArea(new Square(new Position(1), new Position(2)));
        Vectors expectedVector = new Vectors(new HashSet<>());
        expectedVector.add(new Vector(new Square(new Position(1), new Position(3)), Direction.UP));
        expectedVector.add(new Vector(new Square(new Position(1), new Position(4)), Direction.UP));
        expectedVector.add(new Vector(new Square(new Position(2), new Position(3)), Direction.UP_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 블랙_폰_이동가능영역() {
        Vectors movableVector = bPawn.movableArea(new Square(new Position(1), new Position(7)));
        Vectors expectedVector = new Vectors(new HashSet<>());
        expectedVector.add(new Vector(new Square(new Position(1), new Position(6)), Direction.DOWN));
        expectedVector.add(new Vector(new Square(new Position(2), new Position(6)), Direction.DOWN_RIGHT));
        expectedVector.add(new Vector(new Square(new Position(1), new Position(5)), Direction.DOWN));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 화이트_폰_7번행() {
        Vectors movableVector = wPawn.movableArea(new Square(new Position(1), new Position(7)));
        Vectors expectedVector = new Vectors(new HashSet<>());
        expectedVector.add(new Vector(new Square(new Position(1), new Position(8)), Direction.UP));
        expectedVector.add(new Vector(new Square(new Position(2), new Position(8)), Direction.UP_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }

    @Test
    void 블랙_폰_2번행() {
        Vectors movableVector = bPawn.movableArea(new Square(new Position(1), new Position(2)));
        Vectors expectedVector = new Vectors(new HashSet<>());
        expectedVector.add(new Vector(new Square(new Position(1), new Position(1)), Direction.DOWN));
        expectedVector.add(new Vector(new Square(new Position(2), new Position(1)), Direction.DOWN_RIGHT));

        assertThat(expectedVector).isEqualTo(movableVector);
    }
}
