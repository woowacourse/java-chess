package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.point.Point;
import chess.domain.point.Points;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.*;
import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ChessTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(() -> Chess.create(Board.create(), Points.create()));
    }

    @DisplayName("기물을 움직인다.")
    @ParameterizedTest
    @MethodSource("moveDummy")
    void move(final Position source, final Position target, final Color player) {
        // given
        final Chess chess = Chess.create(Board.create(), Points.create());

        // when
        final Piece targetPiece = chess.getBoardValue().get(target);
        final Piece piece = chess.move(source, target, player);

        // then
        assertThat(piece).isEqualTo(targetPiece);
    }

    static Stream<Arguments> moveDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_0_6, POSITION_0_5, WHITE),
                Arguments.arguments(POSITION_0_6, POSITION_0_4, WHITE),
                Arguments.arguments(POSITION_1_6, POSITION_1_5, WHITE),
                Arguments.arguments(POSITION_1_6, POSITION_1_4, WHITE),
                Arguments.arguments(POSITION_2_6, POSITION_2_5, WHITE),
                Arguments.arguments(POSITION_2_6, POSITION_2_4, WHITE)
        );
    }

    @DisplayName("체스 우승자 색상을 찾는다.")
    @ParameterizedTest
    @MethodSource("findWinnerDummy")
    void findWinner(
            final List<Position> sources,
            final List<Position> targets,
            final List<Color> turns,
            final Color expected
    ) {
        // given
        final Chess chess = Chess.create(Board.create(), Points.create());

        // when
        final int size = sources.size();
        for (int index = 0; index < size; index++) {
            chess.move(sources.get(index), targets.get(index), turns.get(index));
        }
        final Color winner = chess.findWinner();


        // then
        assertThat(winner).isEqualTo(expected);
    }

    static Stream<Arguments> findWinnerDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(),
                        List.of(),
                        List.of(),
                        EMPTY
                ),
                /*
                    RNBQKBNR
                    P.PPPPPP
                    ........
                    .P......
                    ..p.....
                    ........
                    pp.ppppp
                    rnbqkbnr
                 */
                Arguments.arguments(
                        List.of(POSITION_2_6, POSITION_1_1),
                        List.of(POSITION_2_4, POSITION_1_3),
                        List.of(WHITE, BLACK),
                        EMPTY
                ),
                /*
                    RNBQKBNR
                    P.PPPPPP
                    ........
                    .p......
                    ........
                    ........
                    pp.ppppp
                    rnbqkbnr
                 */
                Arguments.arguments(
                        List.of(POSITION_2_6, POSITION_1_1, POSITION_2_4),
                        List.of(POSITION_2_4, POSITION_1_3, POSITION_1_3),
                        List.of(WHITE, BLACK, WHITE),
                        EMPTY
                ),
                /*
                    .NBQKBNR
                    .PPPPPPP
                    ........
                    R.......
                    ........
                    ........
                    p.pppppp
                    rnbqkbnr
                 */
                Arguments.arguments(
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_4, POSITION_0_0),
                        List.of(POSITION_1_4, POSITION_0_3, POSITION_0_3, POSITION_0_3),
                        List.of(WHITE, BLACK, WHITE, BLACK),
                        EMPTY
                ),
                /*
                    .NBQKBNR
                    .PPPPPPP
                    ........
                    ........
                    R.......
                    ........
                    ..pppppp
                    rnbqkbnr
                 */
                Arguments.arguments(
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_4, POSITION_0_0, POSITION_0_6, POSITION_0_3),
                        List.of(POSITION_1_4, POSITION_0_3, POSITION_0_3, POSITION_0_3, POSITION_0_4, POSITION_0_4),
                        List.of(WHITE, BLACK, WHITE, BLACK, WHITE, BLACK),
                        BLACK
                )
        );
    }

    @DisplayName("선택한 색상 현재 총점수를 반환한다.")
    @ParameterizedTest
    @MethodSource("findPointByDummy")
    void findPointBy(
            final List<Position> sources,
            final List<Position> targets,
            final List<Color> turns,
            final Color current,
            final Point expected
    ) {
        // given
        final Chess chess = Chess.create(Board.create(), Points.create());

        // when
        final int size = sources.size();
        for (int index = 0; index < size; index++) {
            chess.move(sources.get(index), targets.get(index), turns.get(index));
        }
        final Point point = chess.findPointBy(current);

        // then
        assertThat(point).isEqualTo(expected);
    }

    static Stream<Arguments> findPointByDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(),
                        List.of(),
                        List.of(),
                        EMPTY,
                        Point.create(0)
                ),
                Arguments.arguments(
                        List.of(POSITION_2_6, POSITION_1_1),
                        List.of(POSITION_2_4, POSITION_1_3),
                        List.of(WHITE, BLACK),
                        BLACK,
                        Point.create(38)
                ),
                Arguments.arguments(
                        List.of(POSITION_2_6, POSITION_1_1, POSITION_2_4),
                        List.of(POSITION_2_4, POSITION_1_3, POSITION_1_3),
                        List.of(WHITE, BLACK, WHITE),
                        BLACK,
                        Point.create(37)
                ),
                Arguments.arguments(
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_4, POSITION_0_0),
                        List.of(POSITION_1_4, POSITION_0_3, POSITION_0_3, POSITION_0_3),
                        List.of(WHITE, BLACK, WHITE, BLACK),
                        BLACK,
                        Point.create(37)
                ),
                Arguments.arguments(
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_4, POSITION_0_0, POSITION_0_6, POSITION_0_3),
                        List.of(POSITION_1_4, POSITION_0_3, POSITION_0_3, POSITION_0_3, POSITION_0_4, POSITION_0_4),
                        List.of(WHITE, BLACK, WHITE, BLACK, WHITE, BLACK),
                        WHITE,
                        Point.create(36)
                )
        );
    }

}
