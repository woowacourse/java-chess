package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    @DisplayName("Position 캐싱이 잘 되었는지 확인")
    void positionCache() {
        Position position = Position.of("a8");
        assertThat(position).isSameAs(Position.of("a8"));
        assertThat(position).isEqualTo(Position.of("a8"));
    }

    @Test
    @DisplayName("Position 에 넣는 값 타입에 따라 잘 생성하는지 확인")
    void positionInput() {
        Position position = Position.of("a8");
        assertThat(position).isEqualTo(Position.of(0, 0));
        assertThat(position).isSameAs(Position.of(0, 0));
        assertThat(position).isEqualTo(Position.of(Row.EIGHT, Column.A));
        assertThat(position).isSameAs(Position.of(Row.EIGHT, Column.A));
    }
}
