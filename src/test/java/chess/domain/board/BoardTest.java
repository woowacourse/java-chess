package chess.domain.board;

import chess.dao.BoardDAO;
import chess.dao.FakeBoardDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    static Stream<Arguments> piecePlaceParams() {
        return Stream.of(
                Arguments.of("빈 칸 확인", Row.FOUR, Column.D, Optional.empty(),
                        Arguments.of("폰 확인", Row.TWO, Column.D, Optional.of(Piece.of(Type.PAWN, Side.WHITE))),
                        Arguments.of("퀸 확인", Row.ONE, Column.D, Optional.of(Piece.of(Type.QUEEN, Side.WHITE))),
                        Arguments.of("룩 확인", Row.EIGHT, Column.A, Optional.of(Piece.of(Type.ROOK, Side.BLACK))))
        );
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
                    false, false, true
            ),
                Arguments.of("아군 나이트 경로 테스트 (비지 않은 목적지 칸)",
                        Position.of(Row.ONE, Column.B), Position.of(Row.TWO, Column.D),
                        false, false, false
                )
        );
    }

    @DisplayName("판 초기화 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("piecePlaceParams")
    void findPieceTest(String message, Row row, Column column, Optional<Piece> expected) throws SQLException {
        Board board = new Board(new FakeBoardDAO());
        board.initialize();

        assertThat(board.findPieceOn(Position.of(row, column))).isEqualTo(expected);
    }

    @DisplayName("경로 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("generatePathParams")
    void generatePath(String message, Position start, Position end,
                      boolean expectedBlocked, boolean expectedEnemyOnEnd, boolean expectedEmptyEnd) throws SQLException {
        Board board = new Board(new FakeBoardDAO());
        board.initialize();
        Path path = board.generatePath(start, end);
        assertThat(path.isBlocked()).isEqualTo(expectedBlocked);
        assertThat(path.isEnemyOnEnd()).isEqualTo(expectedEnemyOnEnd);
        assertThat(path.isEndEmpty()).isEqualTo(expectedEmptyEnd);
    }

    @DisplayName("판 카운트 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("countParams")
    void count(String message, Type type, Side side, long expected) throws SQLException {
        Board board = new Board(new FakeBoardDAO());
        board.initialize();
        board.move(Position.of("b2"), Position.of("b3"));
        board.move(Position.of("b7"), Position.of("b6"));
        board.move(Position.of("c1"), Position.of("a3"));
        board.move(Position.of("a7"), Position.of("a6"));
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
    void countPawnsOnSameColumn() throws SQLException {
        BoardDAO boardDAO = new FakeBoardDAO(new HashMap<Position, Piece>() {{
            put(Position.of(Row.EIGHT, Column.A), Piece.of(Type.PAWN, Side.WHITE));
            put(Position.of(Row.FIVE, Column.A), Piece.of(Type.PAWN, Side.WHITE));
            put(Position.of(Row.FOUR, Column.A), Piece.of(Type.PAWN, Side.WHITE));

            put(Position.of(Row.EIGHT, Column.B), Piece.of(Type.PAWN, Side.BLACK));
            put(Position.of(Row.FOUR, Column.B), Piece.of(Type.PAWN, Side.BLACK));
        }});

        Board board = new Board(boardDAO);

        assertThat(board.countPawnsOnSameColumn(Side.WHITE)).isEqualTo(3);
        assertThat(board.countPawnsOnSameColumn(Side.BLACK)).isEqualTo(2);
    }

}
