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
    @DisplayName("캐싱된 포지션을 가져온다")
    void valueOf() {
        Position position = Position.valueOf("a1");
        assertThat(position).isEqualTo(Position.valueOf("a1"));
    }
}
