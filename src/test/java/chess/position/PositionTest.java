package chess.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.position.File.A;
import static chess.position.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("행, 열을 입력받아 생성한 Position 객체가 올바르게 생성되는지 테스트")
    @Test
    void ofTest() {
        assertThat(Position.of("a1")).isEqualTo(Position.of(A, ONE));
    }

    @Test
    void findDiagonalTraceTest() {
        List<Position> trace1 = Position.findDiagonalTrace(Position.of("a1"), Position.of("d4"));
        List<Position> trace2 = Position.findDiagonalTrace(Position.of("a4"), Position.of("d1"));
        assertThat(trace1).containsExactlyInAnyOrder(Position.of("b2"), Position.of("c3"));
        assertThat(trace2).containsExactlyInAnyOrder(Position.of("b3"), Position.of("c2"));
    }
}