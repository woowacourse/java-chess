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
            .isInstanceOf(LocationCacheMissException.class);
        assertThatThrownBy(() -> Location.of(9, 0))
            .isInstanceOf(LocationCacheMissException.class);
        assertThatThrownBy(() -> Location.of(1, 9))
            .isInstanceOf(LocationCacheMissException.class);
    }

    @DisplayName("객체 생성 및 유효성 검사 - 문자열 좌표")
    @Test
    void create_inputValue() {
        // given
        String input1 = "a1";
        String input2 = "y1";

        // when
        Location location = Location.convert(input1);

        // then
        assertThat(location.getX()).isEqualTo(1);
        assertThat(location.getY()).isEqualTo(1);
        assertThatThrownBy(() -> Location.convert(input2))
            .isInstanceOf(LocationCacheMissException.class);
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

    @DisplayName("정상적으로 캐시에서 값을 가져온 경우 동일한 객체가 생성된다.")
    @Test
    void cache_hit() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                assertThat(Location.of(x, y)).isSameAs(Location.of(x, y));
            }
        }
    }
}
