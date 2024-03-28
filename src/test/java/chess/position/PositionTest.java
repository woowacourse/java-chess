package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PositionTest {

    @Test
    @DisplayName("동등성을 올바르게 판단한다.")
    void equalsTest() {
        // given
        Position position = Position.of(File.A, Rank.ONE);
        // when, then
        assertThat(position).isEqualTo(Position.of(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("두 위치로 단위 방향을 생성한다.")
    void createFromPositionsTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.D, Rank.FOUR);
        // when
        UnitMovement unitMovement = source.unitMovementToward(destination);
        // then
        assertThat(unitMovement).isEqualTo(UnitMovement.differencesOf(1, 1));
    }

    @Test
    @DisplayName("음의 변화량을 가진 단위 방향을 올바르게 생성한다.")
    void negativeDifferenceTest() {
        // given
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = Position.of(File.A, Rank.ONE);
        // when
        UnitMovement unitMovement = source.unitMovementToward(destination);
        // then
        assertThat(unitMovement).isEqualTo(UnitMovement.differencesOf(-1, -1));
    }


    @Test
    @DisplayName("수직 단위 방향을 올바르게 계산한다.")
    void verticalUnitDirectionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.FOUR);
        // when
        UnitMovement unitMovement = source.unitMovementToward(destination);
        // then
        assertThat(unitMovement).isEqualTo(UnitMovement.differencesOf(0, 1));
    }

    @Test
    @DisplayName("수평 단위 방향을 올바르게 계산한다.")
    void horizontalUnitDirectionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.D, Rank.ONE);
        // when
        UnitMovement unitMovement = source.unitMovementToward(destination);
        // then
        assertThat(unitMovement).isEqualTo(UnitMovement.differencesOf(1, 0));
    }

    @ParameterizedTest
    @EnumSource(value = File.class, names = {"A", "C"})
    @DisplayName("다른 File에 위치하는 경우를 올바르게 확인한다.")
    void differentFileTest(File file) {
        // given
        Position source = Position.of(File.B, Rank.ONE);
        // when
        boolean actual = source.hasFileOf(file);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("같은 File에 위치하는 경우를 올바르게 확인한다.")
    void sameFileTest() {
        // given
        Position source = Position.of(File.B, Rank.ONE);
        // when
        boolean actual = source.hasFileOf(File.B);
        // then
        assertThat(actual).isTrue();
    }
}
