package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    @Test
    @DisplayName("Kinght 객체는 상하 좌우로 1칸 움직인 뒤 대각선 방향으로 이동가능")
    void moveTest() {
        Piece knight = Knight.of(Position.of("b1"), Player.WHITE);
        knight.move(Position.of("c3"));
        assertThat(knight.getPosition()).isEqualTo(Position.of("c3"));
    }

    @Test
    @DisplayName("knight 객체의 이동 규칙으로 도달할 수 없는 position일 때 예외 발생")
    void moveException() {
        Piece knight = Knight.of(Position.of("b1"), Player.WHITE);
        assertThatThrownBy(() -> knight.move(Position.of("c4")))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
