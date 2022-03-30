package chess.position;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @Test
    @DisplayName("수직 방향 확인")
    void isVerticalWay() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(A, SEVEN);
        assertThat(from.isVerticalWay(to)).isTrue();
    }

    @Test
    @DisplayName("수평 방향 확인")
    void isHorizontalWay() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(B, EIGHT);
        assertThat(from.isHorizontalWay(to)).isTrue();
    }

    @Test
    @DisplayName("대각선 방향 확인")
    void isDiagonalWay() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(B, SEVEN);
        assertThat(from.isDiagonalWay(to)).isTrue();
    }

    @Test
    @DisplayName("근접한 위치 확인")
    void isAdjacent() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(B, SEVEN);
        assertThat(from.isAdjacent(to)).isTrue();
    }

    @Test
    @DisplayName("윗쪽 방향 확인")
    void isUpward() {
        Position from = new Position(A, SEVEN);
        Position to = new Position(A, EIGHT);
        assertThat(from.isUpward(to)).isTrue();
    }

    @Test
    @DisplayName("아랫쪽 방향 확인")
    void isDownward() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(A, SEVEN);
        assertThat(from.isDownward(to)).isTrue();
    }

    @Test
    @DisplayName("동일한 Rank 확인")
    void isSameRank() {
        Position position = new Position(A, EIGHT);
        assertThat(position.isSameRank(EIGHT)).isTrue();
    }

    @Test
    @DisplayName("동일한 File 확인")
    void isSameFile() {
        Position position = new Position(A, EIGHT);
        assertThat(position.isSameFile(A)).isTrue();
    }

    @Test
    @DisplayName("수직 거리 확인")
    void getVerticalDistance() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(A, FIVE);
        assertThat(from.getVerticalDistance(to)).isEqualTo(3);
    }

    @Test
    @DisplayName("수평 거리 확인")
    void getHorizontalDistance() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(H, EIGHT);
        assertThat(from.getHorizontalDistance(to)).isEqualTo(7);
    }

    @ParameterizedTest
    @DisplayName("일직선 상의 경로 확인")
    @MethodSource("provideLinearPathPositions")
    void getLinearPath(Position from, Position to, Collection<Position> expectedPath) {
        assertThat(from.getLinearPath(to)).containsExactlyElementsOf(expectedPath);
    }

    private static Stream<Arguments> provideLinearPathPositions() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE),
                        List.of(new Position(A, EIGHT), new Position(A, SEVEN),
                                new Position(A, SIX), new Position(A, FIVE))),
                Arguments.of(new Position(A, FIVE), new Position(A, EIGHT),
                        List.of(new Position(A, FIVE), new Position(A, SIX),
                                new Position(A, SEVEN), new Position(A, EIGHT))),
                Arguments.of(new Position(A, EIGHT), new Position(C, EIGHT),
                        List.of(new Position(A, EIGHT), new Position(B, EIGHT), new Position(C, EIGHT))),
                Arguments.of(new Position(C, EIGHT), new Position(A, EIGHT),
                        List.of(new Position(C, EIGHT), new Position(B, EIGHT), new Position(A, EIGHT))),
                Arguments.of(new Position(A, EIGHT), new Position(D, FIVE),
                        List.of(new Position(A, EIGHT), new Position(B, SEVEN),
                                new Position(C, SIX), new Position(D, FIVE))),
                Arguments.of(new Position(D, FIVE), new Position(A, EIGHT),
                        List.of(new Position(D, FIVE), new Position(C, SIX),
                                new Position(B, SEVEN), new Position(A, EIGHT))),
                Arguments.of(new Position(D, EIGHT), new Position(A, FIVE),
                        List.of(new Position(D, EIGHT), new Position(C, SEVEN),
                                new Position(B, SIX), new Position(A, FIVE))),
                Arguments.of(new Position(A, FIVE), new Position(D, EIGHT),
                        List.of(new Position(A, FIVE), new Position(B, SIX),
                                new Position(C, SEVEN), new Position(D, EIGHT))));
    }

    @Test
    @DisplayName("일직선 상의 경로가 아닌 경우 예외 발생")
    void throwExceptionWhenGetNotLinearPath() {
        Position from = new Position(A, EIGHT);
        Position to = new Position(B, ONE);
        assertThatThrownBy(() -> from.getLinearPath(to))
                .isInstanceOf(IllegalArgumentException.class);
    }
}