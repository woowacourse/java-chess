package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = Location.of(1, 1);
    }

    @DisplayName("객체 생성 및 비교")
    @Test
    void create() {
        assertThat(location).isEqualTo(Location.of(1, 1));
    }

    @DisplayName("유효성 검사")
    @Test
    void create_validation() {
        assertThatThrownBy(() -> Location.of(1, 0))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Location.of(9, 0))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Location.of(1, 9))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수평, 수직 테스트")
    @Test
    void isHorizontalOrVertical_test() {
        // given, when
        Location horizontalTarget = Location.of(5, 1);
        Location verticalTarget = Location.of(1, 5);

        // then
        assertThat(location.isHorizontalOrVertical(horizontalTarget)).isTrue();
        assertThat(location.isHorizontalOrVertical(verticalTarget)).isTrue();
    }

    @DisplayName("대각선 테스트")
    @Test
    void isDiagonal() {
        // given, when
        Location diagonalTarget = Location.of(3, 3);
        Location nonDiaGonamTarget = Location.of(3, 4);

        // then
        assertThat(location.isDiagonal(diagonalTarget)).isTrue();
        assertThat(location.isDiagonal(nonDiaGonamTarget)).isFalse();
    }

    @DisplayName("인접한 위치면 true를 반환한다")
    @Test
    void isAdjacent_true() {
        // given, when
        Location diagonalTargetLocation = Location.of(2, 2);
        Location horizontalTargetLocation = Location.of(2, 1);
        Location verticalTargetLocation = Location.of(1, 2);

        // then
        assertThat(location.isAdjacent(diagonalTargetLocation)).isTrue();
        assertThat(location.isAdjacent(horizontalTargetLocation)).isTrue();
        assertThat(location.isAdjacent(verticalTargetLocation)).isTrue();
    }

    @DisplayName("인접하지 않은 위치면 false를 반환한다")
    @Test
    void isAdjacent_false() {
        // given, when
        Location diagonalTargetLocation = Location.of(3, 2);
        Location horizontalTargetLocation = Location.of(3, 1);
        Location verticalTargetLocation = Location.of(1, 3);

        // then
        assertThat(location.isAdjacent(diagonalTargetLocation)).isFalse();
        assertThat(location.isAdjacent(horizontalTargetLocation)).isFalse();
        assertThat(location.isAdjacent(verticalTargetLocation)).isFalse();
    }

    @DisplayName("주어진 이동 거리만큼 이동 테스트")
    @Test
    void moveByStep() {
        // given, when
        Location movedLocation = location.moveByStep(2, 2);

        // then
        assertThat(movedLocation).isEqualTo(Location.of(3, 3));
    }

    @DisplayName("현재위치에서 이동한 목적지가 보드 안에 위치해야 한다.")
    @Test
    void isRangeByStep() {
        // given, when
        boolean inRange = location.isRangeByStep(1, 0);
        boolean notInRange = location.isRangeByStep(-1, 0);

        assertThat(inRange).isTrue();
        assertThat(notInRange).isFalse();
    }
}
