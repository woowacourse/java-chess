package chess.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitMovementTest {

    @Test
    @DisplayName("File/Rank 차이가 0으로 생성하는 경우, 예외를 발생한다.")
    void invalidDifferencesTest() {
        assertThatThrownBy(() -> UnitMovement.differencesOf(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("단위 이동은 한 칸 이상 이동해야 합니다.");
    }

    @Test
    @DisplayName("단위 방향의 다음 위치를 올바르게 반환한다.")
    void nextPositionTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        UnitMovement unitMovement = UnitMovement.differencesOf(1, 1);
        // when
        Position nextPosition = unitMovement.nextPosition(source);
        // then
        assertThat(nextPosition).isEqualTo(Position.of(File.B, Rank.TWO));
    }

    @Test
    @DisplayName("동등성을 올바르게 비교한다.")
    void equalsTest() {
        // given
        UnitMovement unitMovement = UnitMovement.differencesOf(1, 1);
        // when, then
        assertThat(unitMovement).isEqualTo(UnitMovement.differencesOf(1, 1));
    }
}
