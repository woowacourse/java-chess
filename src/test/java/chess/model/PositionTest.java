package chess.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @Test
    @DisplayName("위치 값의 동일성을 테스트한다.")
    void position_equals() {
        //given
        Position position = Position.of(File.A, Rank.ONE);

        //then
        assertThat(position).isEqualTo(Position.of(File.A, Rank.ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "i7", "a0", "A1"})
    @DisplayName("위치 범위를 벗어나면 예외를 던진다.")
    void position_exception() {
        assertThatThrownBy(() -> Position.of("a9"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Position.ERROR_INVALID_POSITION_INPUT);
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다")
    void between() {
        //given
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.D, Rank.FOUR);

        List<Position> traceGroup = source.between(target);

        //then
        assertThat(traceGroup).contains(Position.of(File.B, Rank.TWO), Position.of(File.C, Rank.THREE)
            , Position.of(File.C, Rank.TWO), Position.of(File.B, Rank.THREE));
    }
}
