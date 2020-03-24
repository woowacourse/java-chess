import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.Positions;
import chess.domain.position.component.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionsTest {

    @DisplayName("Position 객체의 정적 팩토리 생성 방식 확인")
    @Test
    void equalsTest() {
        Position position1 = Positions.of(Row.A, Column.FIVE);
        Position position2 = Positions.of(Row.A, Column.FIVE);
        Assertions.assertThat(position1).isEqualTo(position2);
    }
}
