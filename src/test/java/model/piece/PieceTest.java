package model.piece;

import model.game.Player;
import model.board.Direction;
import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {
    Piece testPiece;

    @BeforeEach
    void setUp() {
        testPiece = new King(Player.WHITE, Position.of("h4"));
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
    void proceedOnlyOneStepTest() {
        Iterator<Position> i = testPiece.proceedOnlyOneStep(Direction.SOUTH_WEST);
        assertThat(i.next()).isEqualTo(Position.of("g3"));
        assertThat(i.hasNext()).isFalse();
    }

    @Test
    void moveTest() {
        assertThat(testPiece.move(Position.of("c6")).position()).isEqualTo(Position.of("c6"));
        assertThat(testPiece.hasNotMoved()).isFalse();
    }

    @Test
    void sortTest() {
        List<Piece> testPieces = Arrays.asList(
                new King(Player.BLACK, Position.of("b6")),
                new Pawn(Player.WHITE, Position.of("c7")),
                new Bishop(Player.BLACK, Position.of("a2"))
        );
        Collections.sort(testPieces);
        assertThat(
                testPieces.stream()
                        .map(Piece::position)
                        .collect(Collectors.toList())
        ).isEqualTo(
                Stream.of(
                        Position.of("a2"),
                        Position.of("b6"),
                        Position.of("c7")
                ).collect(Collectors.toList())
        );
    }
}