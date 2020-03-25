package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {
    @DisplayName("판 초기화 테스트")
    @ParameterizedTest
    @MethodSource("boardInitParams")
    void BoardInitTest(Row row, Column column, String expectedName) {
        Board board = Board.init();

        assertThat(board.findPieceBy(Position.of(Row.FOUR, Column.D))).isEmpty();
        assertThat(getActual(board, row, column)).isEqualTo(expectedName);
    }

    static Stream<Arguments> boardInitParams() {
        return Stream.of(
            Arguments.of(Row.TWO, Column.D, "p"),
            Arguments.of(Row.ONE, Column.D, "q"),
            Arguments.of(Row.EIGHT, Column.A, "r")
        );
    }

    private String getActual(final Board board, Row row, Column column) {
        return board.findPieceBy(Position.of(row, column)).get().name();
    }

    // @DisplayName("판 카운트 테스트")
    // @Test
    // void BoardCountTest() {
    //     Board board = Board.init();
    //
    //     assertThat(board.count(Type.PAWN, Side.BLACK)).isEqualTo(8);
    //     assertThat(board.count(Type.ROOK, Side.BLACK)).isEqualTo(2);
    // }
}
