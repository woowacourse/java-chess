package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class BishopTest {
    @Test
    @DisplayName("비숍 정상 생성되는지 확인한다")
    void init() {
        assertThatCode(() -> new Bishop())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍이 규칙상 정상적으로 움직일 수 있는 경로가 주어지면, 참을 반환한다.")
    void check_position_rule_valid_test() {
        final Bishop bishop = new Bishop();
        final Position start = Position.of("c1");
        final Position validDestination = Position.of("e3");
        assertThat(bishop.checkPositionRule(start, validDestination)).isTrue();
    }

    @Test
    @DisplayName("비숍이 규칙상 정상적으로 움직일 수 없는 경로가 주어지면, 거짓을 반환한다.")
    void check_position_rule_invalid_test() {
        final Bishop bishop = new Bishop();
        final Position start = Position.of("c1");
        final Position invalidDestination = Position.of("d3");
        assertThat(bishop.checkPositionRule(start, invalidDestination)).isFalse();
    }
}
