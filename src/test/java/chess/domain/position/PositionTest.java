package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PositionTest {
    @Test
    @DisplayName("Position을 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Position('a', '2');
            new Position("a2");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Position의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Position actualPosition = new Position("a2");
        Position expectedPosition = new Position('a', '2');
        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @DisplayName("체스판 범위에서 벗어나면 False, 아니면 True")
    @CsvSource(value = { "i3:false", "a9:false", "a3:true" }, delimiter = ':')
    public void isInValidRange(String position, boolean result) {
        assertThat(new Position(position).isInValidRange()).isEqualTo(result);
    }

    @Test
    @DisplayName("특정 위치에서 다른 위치만큼 정상적으로 이동하는 지 테스트")
    public void next() {
        Position position = new Position("a2");
        assertThat(position.next(1, 2)).isEqualTo(new Position("b4"));
    }
}
