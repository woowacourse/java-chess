package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

public class BoardTest {
    @DisplayName("판 초기화 테스트")
    @ParameterizedTest
    @MethodSource("boardInitParams")
    void BoardInitTest(Row row, Column column, Piece expected) {
        Board board = Board.init();

        assertThat(board.findPieceBy(Position.of(Row.FOUR, Column.D))).isEmpty();
        assertThat(getActual(board, row, column)).isEqualTo(expected);
    }

    static Stream<Arguments> boardInitParams() {
        return Stream.of(
            Arguments.of(Row.TWO, Column.D, Piece.of(Type.PAWN, Side.WHITE)),
            Arguments.of(Row.ONE, Column.D, Piece.of(Type.QUEEN, Side.WHITE)),
            Arguments.of(Row.EIGHT, Column.A, Piece.of(Type.ROOK, Side.BLACK))
        );
    }

    private Piece getActual(final Board board, Row row, Column column) {
        return board.findPieceBy(Position.of(row, column)).get();
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

    @DisplayName("판 카운트 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("countParams")
    void count(String message, Type type, Side side, long expected) {
        Board board = Board.init();
        board.move(Position.of("b2"), Position.of("b3"));
        board.move(Position.of("c1"), Position.of("a3"));
        board.move(Position.of("a3"), Position.of("e7"));
        assertThat(board.count(type, side)).isEqualTo(expected);
    }

    static Stream<Arguments> countParams() {
        return Stream.of(
            Arguments.of("검정 폰 개수", Type.PAWN, Side.BLACK, 7),
            Arguments.of("흰색 폰 개수", Type.PAWN, Side.WHITE, 8),
            Arguments.of("검정 룩 개수", Type.ROOK, Side.BLACK, 2)
        );
    }

    @DisplayName("폰 세로 정렬된 개수 세기")
    @Test
    void countPawnsOnSameColumn() {
        Board board = Board.init();
        board.move(Position.of("b2"), Position.of("b4"));
        board.move(Position.of("b4"), Position.of("b5"));
        board.move(Position.of("b5"), Position.of("b6"));
        board.move(Position.of("b6"), Position.of("c7"));
        assertThat(board.countPawnsOnSameColumn(Side.WHITE)).isEqualTo(2);
        assertThat(board.countPawnsOnSameColumn(Side.BLACK)).isEqualTo(0);
    }

}
