package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void createPosition() {
        assertThatCode(() -> Position.of(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("위치는 가로, 세로 범위는 각각 1 ~ 8 이다.")
    @CsvSource({"0,1", "1,0", "1, 9", "9, 1"})
    void createPositionThrowException(int file, int rank) {
        assertThatThrownBy(() -> Position.of(file, rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
    }

    @Test
    @DisplayName("point로부터 위치를 생성한다.")
    void of() {
        assertThat(Position.of(new Point(1, 2))).isEqualTo(Position.of(1, 2));
    }

    @Test
    @DisplayName("point의 범위가 보드 범위를 벗어난 경우 예외가 발생한다.")
    void ofThrowException() {
        assertThatThrownBy(() -> Position.of(new Point(9, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
    }

    @Test
    @DisplayName("이동 거리만큼 이동할 수 있다.")
    void moveByDistance() {
        Position position = Position.of(2, 2);
        assertAll(
                () -> assertThat(position.moveByDistance(new MoveDistance(1, 2))).isEqualTo(Position.of(3, 4)),
                () -> assertThat(position.moveByDistance(new MoveDistance(-1, 2))).isEqualTo(Position.of(1, 4)),
                () -> assertThat(position.moveByDistance(new MoveDistance(0, 2))).isEqualTo(Position.of(2, 4)),
                () -> assertThat(position.moveByDistance(new MoveDistance(0, 0))).isEqualTo(Position.of(2, 2))
        );
    }

    @Test
    @DisplayName("이동 거리만큼 이동하였을 때, 범위를 초과한 경우 예외가 발생한다.")
    void notMoveByDistance() {
        Position position = Position.of(2, 2);
        assertThatThrownBy(() -> assertThat(position.moveByDistance(new MoveDistance(-7, 2))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @Test
    @DisplayName("위치의 file이 최소인 경우 참을 반환한다.")
    void isMinimumFile() {
        Position position = Position.of(1, 3);
        assertThat(position.isMinimumFile()).isTrue();
    }


    @Test
    @DisplayName("위치의 file이 최대인 경우 참을 반환한다.")
    void isMaximumFile() {
        Position position = Position.of(8, 3);
        assertThat(position.isMaximumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최소인 경우 참을 반환한다.")
    void isMinimumRank() {
        Position position = Position.of(3, 1);
        assertThat(position.isMinimumRank()).isTrue();
    }


    @Test
    @DisplayName("위치의 rank가 최대인 경우 참을 반환한다.")
    void isMaximumRank() {
        Position position = Position.of(3, 8);
        assertThat(position.isMaximumRank()).isTrue();
    }


    @Test
    @DisplayName("최대 최소가 아닌 값이 나올 경우 거짓을 반환한다.")
    void isNotBoundaryValue() {
        Position position = Position.of(3, 3);
        assertAll(
                () -> assertThat(position.isMinimumFile()).isFalse(),
                () -> assertThat(position.isMaximumFile()).isFalse(),
                () -> assertThat(position.isMinimumRank()).isFalse(),
                () -> assertThat(position.isMaximumRank()).isFalse()
        );
    }
}
