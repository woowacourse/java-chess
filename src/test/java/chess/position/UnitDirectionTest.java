package chess.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitDirectionTest {

    @Test
    @DisplayName("두 위치가 주어지면, 단위 방향을 반환한다.")
    void createFromPositionsTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.D, Rank.FOUR);
        // when
        UnitDirection unitDirection = UnitDirection.differencesBetween(source, destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(1, 1));
    }

    @Test
    @DisplayName("음의 변화량을 가진 단위 반향을 올바르게 생성한다.")
    void negativeDifferenceTest() {
        // given
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = Position.of(File.A, Rank.ONE);
        // when
        UnitDirection unitDirection = UnitDirection.differencesBetween(source, destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(-1, -1));
    }

    @Test
    @DisplayName("File/Rank 차이가 0으로 생성하는 경우, 예외를 발생한다.")
    void invalidDifferencesTest() {
        assertThatThrownBy(() -> UnitDirection.differencesOf(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("단위 이동은 한 칸 이상 이동해야 합니다.");
    }

    @Test
    @DisplayName("수직 단위 방향을 올바르게 계산한다.")
    void verticalUnitDirectionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.FOUR);
        // when
        UnitDirection unitDirection = UnitDirection.differencesBetween(source, destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(0, 1));
    }

    @Test
    @DisplayName("수평 단위 방향을 올바르게 계산한다.")
    void horizontalUnitDirectionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.D, Rank.ONE);
        // when
        UnitDirection unitDirection = UnitDirection.differencesBetween(source, destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(1, 0));
    }

    @Test
    @DisplayName("단위 방향의 다음 위치를 올바르게 반환한다.")
    void nextPositionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.D, Rank.FOUR);
        UnitDirection unitDirection = UnitDirection.differencesBetween(source, destination);
        // when
        Position nextPosition = unitDirection.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(Position.of(File.B, Rank.TWO));
    }

    @Test
    @DisplayName("동등성을 올바르게 비교한다.")
    void equalsTest() {
        // given
        UnitDirection unitDirection = UnitDirection.differencesOf(1, 1);
        // when, then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(1, 1));
    }
}
