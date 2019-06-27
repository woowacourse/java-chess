package model.board;

import model.piece.Piece;
import model.piece.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board testBoard;

    @BeforeEach
    void setUp() {
        testBoard = new Board();
    }

    @Test
    void initTest() {
        List<Piece> pieces = testBoard.getPieces().collect(Collectors.toList());
        List<String> pieceSymbols = Arrays.asList(
                "♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖", "♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙",
                "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"
        );
        assertThat(
                IntStream.range(0, 32)
                        .mapToObj(i -> pieces.get(i).toString().equals(pieceSymbols.get(i)))
                        .allMatch(x -> x)
        ).isTrue();
    }

    @Test
    void getPieceAtTest() {
        assertThat(
                testBoard.getPieceAt(Position.of("c7")).get().position()
        ).isEqualTo(Position.of("c7"));
    }

    @Test
    void getPieceTestAtWrongPosition() {
        assertThatThrownBy(
                () -> testBoard.getPieceAt(Position.of("j1")).get().position()
        );
    }

    @Test
    void removePieceTest() {
        Position pos = Position.of("d2");
        assertThat(testBoard.removePieceAt(pos)).isTrue();
        assertThat(testBoard.getPieceAt(pos).isPresent()).isFalse();
    }

    @Test
    void moveTest() {
        testBoard.movePieceFromTo(Position.of("e1"), Position.of("c6"));
        assertThat(testBoard.getPieceAt(Position.of("c6")).get().isKing()).isTrue();
    }

    @Test
    void changeTypeTest() {
        Piece p = testBoard.getPieceAt(Position.of("a2")).get();
        assertThat(p.hasNotMoved()).isTrue();
        assertThat(testBoard.movePieceFromTo(Position.of("a2"), Position.of("a4"))).isTrue();
        assertThat(p.hasNotMoved()).isFalse();
        try {
            assertThat(testBoard.changeTypeOfPieceAt(Position.of("a4"), Queen.class)).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Piece q = testBoard.getPieceAt(Position.of("a4")).get();
        assertThat(q.isPawn()).isFalse();
        assertThat(q.hasNotMoved()).isFalse();
        assertThat(q instanceof Queen).isTrue();
    }
}