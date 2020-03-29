package chess.domain.move;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoveTypeFactoryTest {
    @Test
    @DisplayName("이동 거리가 일직선일 경우 MoveType이 StraightType이 된다")
    void straightTypeTest() {
        MoveType straightType = MoveTypeFactory.of(Position.of("a1"), Position.of("a5"));
        assertThat(straightType).isInstanceOf(StraightType.class);
    }

    @Test
    @DisplayName("이동 거리가 대각선일 경우 MoveType이 CrossType이 된다")
    void crossTypeTest() {
        MoveType straightType = MoveTypeFactory.of(Position.of("a1"), Position.of("e5"));
        assertThat(straightType).isInstanceOf(CrossType.class);
    }

    @Test
    @DisplayName("이동 거리가 일직선일 경우 MoveType이 StraightType이 된다")
    void knightTypeTest() {
        MoveType straightType = MoveTypeFactory.of(Position.of("a1"), Position.of("a3"));
        assertThat(straightType).isInstanceOf(StraightType.class);
    }
}