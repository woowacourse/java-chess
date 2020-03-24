package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @Test
    void moveTest() {
        Piece knight = new Knight(Position.of("b1"), Player.WHITE);
        knight.move(Position.of("c3"));
        assertThat(knight.getPosition()).isEqualTo(Position.of("c3"));
    }
}
