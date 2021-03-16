package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"-1:0", "9:0", "0:-1", "0:9"}, delimiter = ':')
    @DisplayName("Board 범위 벗어나는 예외 처리 테스트")
    void testOf_IllegalArgumentException(int x, int y) {
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Position.of(x, y))
                .withMessage("체스판을 넘어서는 범위입니다.");
    }

    @Test
    @DisplayName("Postion 생성 테스트")
    void testOf() {
        //given
        int x = 0;
        int y = 0;

        //when
        Position position = Position.of(x, y);

        //then
        assertThat(position).isEqualTo(Position.of(0, 0));
        assertThat(position).isSameAs(Position.of(0, 0));
    }
}
