package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        UnitDirection unitDirection = source.unitDirectionToward(destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(1, 1));
    }

    @Test
    @DisplayName("음의 변화량을 가진 단위 방향을 올바르게 생성한다.")
    void negativeDifferenceTest() {
        // given
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = Position.of(File.A, Rank.ONE);
        // when
        UnitDirection unitDirection = source.unitDirectionToward(destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(-1, -1));
    }


    @Test
    @DisplayName("수직 단위 방향을 올바르게 계산한다.")
    void verticalUnitDirectionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.FOUR);
        // when
        UnitDirection unitDirection = source.unitDirectionToward(destination);
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
        UnitDirection unitDirection = source.unitDirectionToward(destination);
        // then
        assertThat(unitDirection).isEqualTo(UnitDirection.differencesOf(1, 0));
    }
}
