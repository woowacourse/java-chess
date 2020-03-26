import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionsTest {

    @DisplayName("Position 객체의 정적 팩토리 생성 방식 확인")
    @Test
    void equalsTest() {
        Position position1 = Positions.of(Row.A, Column.FIVE);
        Position position2 = Positions.of(Row.A, Column.FIVE);
        Assertions.assertThat(position1).isEqualTo(position2);
    }

    @DisplayName("Position 생성: 정상적으로 생성")
    @ParameterizedTest
    @ValueSource(strings = {"a1, a8, h1, h8"})
    void positionOfTest(String input) {
        Assertions.assertThatThrownBy(() -> Positions.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Position 생성 예외: 체스판의 범위를 벗어났을 때")
    @ParameterizedTest
    @ValueSource(strings = {"a20, i1"})
    void positionOfTestByException(String input) {
        Assertions.assertThatThrownBy(() -> Positions.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
