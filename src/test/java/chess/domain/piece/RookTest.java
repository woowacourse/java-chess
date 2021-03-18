package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class RookTest {
    @Test
    @DisplayName("룩이 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new Rook()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩이 규칙상 정상적으로 움직일 수 있는 경로가 주어지면, 참을 반환한다.")
    void check_position_rule_valid_test() {
        final Rook rook = new Rook();
        final Position start = Position.of("a1");
        final Position validDestination = Position.of("a6");
        assertThat(rook.checkPositionRule(start, validDestination)).isTrue();
    }
}
