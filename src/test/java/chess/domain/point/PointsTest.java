package chess.domain.point;

import chess.domain.Color;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PointsTest {
    private static final Point MAX_SUM_POINT = Point.create(38.0);

    @DisplayName("생성한다.")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(Points::create);
    }

    @DisplayName("점수를 잃다.")
    @ParameterizedTest
    @MethodSource("decreasePointDummy")
    void decreasePoint(final List<Piece> losePieces, final Point expected) {
        // given
        final Points points = Points.create();

        // when
        losePieces.forEach(points::decrease);
        final Point calculatedPoint = points.calculatePoint(BLACK, 0);

        // then
        assertThat(calculatedPoint).isEqualTo(expected);
    }

    static Stream<Arguments> decreasePointDummy() {
        return Stream.of(
                Arguments.arguments(List.of(
                        Pawn.from(BLACK)
                ), Point.create(37)),
                Arguments.arguments(List.of(
                        Pawn.from(BLACK),
                        Bishop.from(BLACK)
                ), Point.create(34)),
                Arguments.arguments(List.of(
                        Bishop.from(BLACK),
                        Bishop.from(BLACK)
                ), Point.create(32)),
                Arguments.arguments(List.of(
                        Rook.from(BLACK),
                        Bishop.from(BLACK)
                ), Point.create(30)),
                Arguments.arguments(List.of(
                        Rook.from(BLACK),
                        Rook.from(BLACK)
                ), Point.create(28)),
                Arguments.arguments(List.of(
                        Bishop.from(BLACK),
                        Bishop.from(BLACK),
                        Rook.from(BLACK),
                        Rook.from(BLACK)
                ), Point.create(22)),
                Arguments.arguments(List.of(
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Pawn.from(BLACK),
                        Bishop.from(BLACK),
                        Bishop.from(BLACK),
                        Rook.from(BLACK),
                        Rook.from(BLACK),
                        Knight.from(BLACK),
                        Knight.from(BLACK),
                        Queen.from(BLACK),
                        King.from(BLACK)
                ), Point.ZERO)
        );
    }

    @DisplayName("해당 색상 기물의 총 점수를 계산하다.")
    @ParameterizedTest
    @MethodSource("calculatePointDummy")
    void calculatePoint(final Color color) {
        // given
        final Points points = Points.create();

        // when
        final Point calculatedPoint = points.calculatePoint(color, 0);

        // then
        assertThat(calculatedPoint).isEqualTo(MAX_SUM_POINT);
    }

    static Stream<Arguments> calculatePointDummy() {
        return Stream.of(
                Arguments.arguments(BLACK),
                Arguments.arguments(WHITE)
        );
    }
}
