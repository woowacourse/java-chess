package chess.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("위치 값의 동일성을 테스트한다.")
    void position_equals() {
        //given
        Position position = Position.of(Rank.ONE, File.A);

        //then
        assertThat(position).isEqualTo(Position.of(Rank.ONE, File.A));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "i7", "a0", "A1"})
    @DisplayName("위치 범위를 벗어나면 예외를 던진다.")
    void position_exception() {
        assertThatThrownBy(() -> Position.of("a9"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다")
    void between() {
        //given
        Position source = Position.of(Rank.ONE, File.A);
        Position target = Position.of(Rank.FOUR, File.D);

        List<Position> traceGroup = source.between(target);

        //then
        assertThat(traceGroup).contains(Position.of(Rank.TWO, File.B), Position.of(Rank.THREE, File.C)
            , Position.of(Rank.TWO, File.C), Position.of(Rank.THREE, File.B));
    }
}
