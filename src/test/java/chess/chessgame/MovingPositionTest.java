package chess.chessgame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovingPositionTest {

    @Test
    @DisplayName("현재 위치와 이동하려는 위치가 같은 경우 예외 발생")
    void checkSamePosition() {
        assertThatThrownBy(() -> new MovingPosition("a1", "a1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재 위치와 같은 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a6:c6", "b7:b5"}, delimiter = ':')
    @DisplayName("직선상에 있는지 확인 - true")
    void isLinearWhenTrue(String from, String to) {
        assertThat(new MovingPosition(from, to).isLinear()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a5:c7", "c5:a7", "d5:f4"}, delimiter = ':')
    @DisplayName("직선상에 있는지 확인 - false")
    void isLinearWhenFalse(String from, String to) {
        assertThat(new MovingPosition(from, to).isLinear()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"a5:c7", "c5:a7"}, delimiter = ':')
    @DisplayName("대각선상에 있는지 확인 - true")
    void isCrossWhenTrue(String from, String to) {
        assertThat(new MovingPosition(from, to).isCross()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a6:c6", "b7:b5", "d5:f4"}, delimiter = ':')
    @DisplayName("대각선상에 있는지 확인 - false")
    void isCrossWhenFalse(String from, String to) {
        assertThat(new MovingPosition(from, to).isCross()).isFalse();
    }

    @Test
    @DisplayName("후보들 중 움직이고자 하는 위치가 있는지 확인 - true")
    void isAnyPossibleWhenTrue() {
        List<Position> coordinates = List.of(
                new Position(-1, 1),
                new Position(3, -1),
                new Position(-2, 2)
        );
        MovingPosition movingPosition = new MovingPosition("f2", "h4");

        assertThat(movingPosition.isAnyPossible(coordinates)).isTrue();
    }

    @Test
    @DisplayName("후보들 중 움직이고자 하는 위치가 있는지 확인 - false")
    void isAnyPossibleWhenFalse() {
        List<Position> coordinates = List.of(
                new Position(-1, 1),
                new Position(3, -1)
        );
        MovingPosition movingPosition = new MovingPosition("f2", "h4");

        assertThat(movingPosition.isAnyPossible(coordinates)).isFalse();
    }

    @Test
    @DisplayName("직선상에서 두 점 사이의 모든 점 계산")
    void computeLinearMiddle() {
        assertThat(new MovingPosition("b5", "f5").computeLinearMiddle())
                .isEqualTo(List.of(
                        new Position(3, 2),
                        new Position(3, 3),
                        new Position(3, 4)
                ));
    }

    @Test
    @DisplayName("대각선상에서 두 점 사이의 모든 점 계산")
    void computeCrossMiddle() {
        assertThat(new MovingPosition("f7", "b3").computeCrossMiddle())
                .isEqualTo(List.of(
                        new Position(4, 2),
                        new Position(3, 3),
                        new Position(2, 4)
                ));
    }

    @Test
    @DisplayName("두 점 사이의 거리가 2이상이라면 한칸 위 계산")
    void computeOneUp() {
        assertThat(new MovingPosition("f2", "f4").computeOneUp())
                .isEqualTo(List.of(
                        new Position(5, 5)
                ));
    }

    @Test
    @DisplayName("두 점 사이의 거리가 1이라면 빈 리스트 반환 - up")
    void computeEmptyOneUp() {
        assertThat(new MovingPosition("f2", "f3").computeOneUp())
                .isEqualTo(List.of());
    }

    @Test
    @DisplayName("두 점 사이의 거리가 2이상이라면 한칸 아래 계산")
    void computeOneDown() {
        assertThat(new MovingPosition("c7", "c5").computeOneDown())
                .isEqualTo(List.of(
                        new Position(2, 2)
                ));
    }

    @Test
    @DisplayName("두 점 사이의 거리가 1이라면 빈 리스트 반환 - down")
    void computeEmptyOneDown() {
        assertThat(new MovingPosition("c7", "c6").computeOneDown())
                .isEqualTo(List.of());
    }
}