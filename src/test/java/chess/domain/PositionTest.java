package chess.domain;

import chess.domain.piece.direction.Direction;
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
    @DisplayName("Position 생성 테스트")
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

    @Test
    @DisplayName("Position이 인자로 받은 direction으로 진행하는지 테스트")
    void testGo() {
        //given
        Position position = Position.of(1, 1);

        //when
        Position expectedPosition = position.go(Direction.UP_RIGHT);

        //then
        assertThat(expectedPosition).isEqualTo(Position.of(2, 2));
    }

    @Test
    @DisplayName("Position이 Board 판을 넘어서는 위치로 이동할 때 예외 테스트")
    void testInvalidGo() {
        //given
        Position position = Position.of(0, 0);

        //when
        boolean expectedMovable = position.invalidGo(Direction.DOWN);

        //then
        assertThat(expectedMovable).isTrue();
    }
}
