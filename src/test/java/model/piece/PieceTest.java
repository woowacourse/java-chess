package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {
    private Piece testPiece;

    @BeforeEach
    void setUp() {
        testPiece = new King(Color.WHITE, Position.of("h4"));
    }

    @Test
    void proceedUntilBlockedTest() {
        Iterator<Position> i = testPiece.proceedUntilBlocked(Direction.NORTH);
        assertThat(i.next()).isEqualTo(Position.of("h5"));
        assertThat(i.next()).isEqualTo(Position.of("h6"));
        assertThat(i.next()).isEqualTo(Position.of("h7"));
        assertThat(i.next()).isEqualTo(Position.of("h8"));
    }

    @Test
    void proceedUntilBlockedTestAtTheEndOfTheBound() {
        assertThatThrownBy(
                () -> testPiece.proceedUntilBlocked(Direction.EAST).next()
        );
    }

    @Test
    void moveTest() {
        testPiece.move(Position.of("c6"));
        assertThat(testPiece.position()).isEqualTo(Position.of("c6"));
        assertThat(testPiece.hasNotMoved()).isFalse();
    }

    @Test
    void sortTest() {
        assertThat(
                Stream.of(
                        new King(Color.BLACK, Position.of("b6")),
                        new Pawn(Color.WHITE, Position.of("c7")),
                        new Bishop(Color.BLACK, Position.of("a2"))
                ).map(Piece::position)
                .sorted()
        ).isEqualTo(Arrays.asList(Position.of("a2"), Position.of("b6"), Position.of("c7"))
        );
    }
}