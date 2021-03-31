package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("체스 좌표를 주면 정수를 담은 Location을 만들어준다.")
    @Test
    void create_input() {
        // given
        String validInput1 = "a1";
        String validInput2 = "b7";
        String validInput3 = "11";
        String validInput4 = "27";

        String inValidInput1 = "h9";
        String inValidInput2 = "z1";

        // then
        assertAll(
            () -> assertThat(Location.of(validInput1)).isEqualTo(Location.of(1, 1)),
            () -> assertThat(Location.of(validInput2)).isEqualTo(Location.of(2, 7)),
            () -> assertThat(Location.of(validInput3)).isEqualTo(Location.of(1, 1)),
            () -> assertThat(Location.of(validInput4)).isEqualTo(Location.of(2, 7)),
            () -> assertThatThrownBy(() -> Location.of(inValidInput1)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> Location.of(inValidInput2)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("위치값은 보드의 범위를 벗어날 수 없다.")
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
        assertThat(location.canMoveHorizontallyOrVerticallyTo(horizontalTarget)).isTrue();
        assertThat(location.canMoveHorizontallyOrVerticallyTo(verticalTarget)).isTrue();
    }

    @DisplayName("대각선 테스트")
    @Test
    void isDiagonal() {
        // given, when
        Location diagonalTarget = Location.of(3, 3);
        Location nonDiaGonamTarget = Location.of(3, 4);

        // then
        assertThat(location.canMoveDigonallyTo(diagonalTarget)).isTrue();
        assertThat(location.canMoveDigonallyTo(nonDiaGonamTarget)).isFalse();
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
