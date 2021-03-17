package domain;

import domain.piece.*;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    Board board;

    private static Stream<Arguments> pieces() {
        return Stream.of(
                Arguments.of(Column.C, Row.SIX, Empty.class),
                Arguments.of(Column.H, Row.THREE, Empty.class),
                Arguments.of(Column.A, Row.SEVEN, Pawn.class),
                Arguments.of(Column.A, Row.EIGHT, Rook.class),
                Arguments.of(Column.B, Row.EIGHT, Knight.class),
                Arguments.of(Column.C, Row.EIGHT, Bishop.class),
                Arguments.of(Column.D, Row.EIGHT, Queen.class),
                Arguments.of(Column.E, Row.EIGHT, King.class),
                Arguments.of(Column.F, Row.EIGHT, Bishop.class),
                Arguments.of(Column.G, Row.EIGHT, Knight.class),
                Arguments.of(Column.H, Row.EIGHT, Rook.class)
        );
    }

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @ParameterizedTest
    @MethodSource("pieces")
    void pieceCreate(Column column, Row row, Class<? extends Piece> expected) {
        assertThat(board.pieceOf(column, row)).isInstanceOf(expected);
    }

    @Test
    void pawnDiff() {
        Piece pawn1 = board.pieceOf(Column.A, Row.SEVEN);
        Piece pawn2 = board.pieceOf(Column.B, Row.SEVEN);
        assertThat(pawn1).isNotSameAs(pawn2);
    }

    @Test
    void display() {
        board.display();
    }
}
