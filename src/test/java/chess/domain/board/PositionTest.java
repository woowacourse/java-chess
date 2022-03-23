package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("포지션 좌표를 String 으로 받아 가져온다.")
    void constructor() {
        assertThat(Position.valueOf("a1")).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("동일한 row에서 크기 비교에 성공한다.")
    void compareToSameRow() {
        Position position = Position.valueOf("a8");
        assertThat(position).isGreaterThan(Position.valueOf("c8"));
    }

    @Test
    @DisplayName("크기 비교에 성공한다.")
    void compareTo() {
        Position position = Position.valueOf("a8");
        assertThat(position).isLessThan(Position.valueOf("a7"));
    }

    @Test
    @DisplayName("캐싱된 포지션을 가져온다.")
    void valueOf() {
        Position position = Position.valueOf("a1");
        assertThat(position).isEqualTo(Position.valueOf("a1"));
    }

    @Test
    @DisplayName("Column 의 value 차이를 구한다.")
    void subtractColumn() {
        Position source = Position.valueOf("a1");
        Position target = Position.valueOf("b2");
        assertThat(source.subtractColumn(target)).isEqualTo(1);
    }

    @Test
    @DisplayName("Row 의 value 차이를 구한다.")
    void subtractRow() {
        Position source = Position.valueOf("a1");
        Position target = Position.valueOf("b2");
        assertThat(source.subtractRow(target)).isEqualTo(1);
    }
}
