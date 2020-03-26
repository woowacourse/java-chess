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

    @DisplayName("경로 생성 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("generatePathParams")
    void generatePath(String message, Position start, Position end,
        boolean expectedBlocked, boolean expectedEnemyOnEnd, boolean expectedEmptyEnd) {
        Board board = Board.init();
        Path path = board.generatePath(start, end);
        assertThat(path.isBlocked()).isEqualTo(expectedBlocked);
        assertThat(path.isEnemyOnEnd()).isEqualTo(expectedEnemyOnEnd);
        assertThat(path.isEndEmpty()).isEqualTo(expectedEmptyEnd);
    }

    static Stream<Arguments> generatePathParams() {
        return Stream.of(
            Arguments.of("상대 폰까지 직선 경로 테스트",
                Position.of(Row.TWO, Column.A), Position.of(Row.SEVEN, Column.A),
                false, true, false
            ),
            Arguments.of("상대 폰까지 대각선 경로 테스트",
                Position.of(Row.TWO, Column.A), Position.of(Row.SEVEN, Column.F),
                false, true, false
            ),
            Arguments.of("아군 룩끼리 경로 테스트",
                Position.of(Row.ONE, Column.A), Position.of(Row.ONE, Column.F),
                true, false, false
            ),
            Arguments.of("상대 룩까지 직선 경로 테스트",
                Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.A),
                true, true, false
            ),
            Arguments.of("아군 나이트 경로 테스트 (빈 목적지 칸)",
                Position.of(Row.ONE, Column.B), Position.of(Row.THREE, Column.C),
                true, false, true
            ),
            Arguments.of("아군 나이트 경로 테스트 (비지 않은 목적지 칸)",
                Position.of(Row.ONE, Column.B), Position.of(Row.TWO, Column.D),
                true, false, false
            )
        );
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
