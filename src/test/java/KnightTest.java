import chess.domain.Player;
import chess.domain.chesspieces.Knight;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class KnightTest {
    // 가능한 방향 다 확인하기
    @Test
    void 정확한위치에나이트가움직이는지확인() {
        Knight knight = new Knight(Player.WHITE);
        Position source = Positions.of("c3");
        Position target = Positions.of("b5");
        Assertions.assertThat(knight.validateMovePosition(source, target)).isTrue();
    }

    @Test
    void 틀린위치에나이트가움직이는지확인() {
        Knight knight = new Knight(Player.WHITE);
        Position source = Positions.of("c3");
        Position target = Positions.of("b4");
        Assertions.assertThat(knight.validateMovePosition(source, target)).isFalse();
    }

    @Test
    void 나이트_모서리_나이트가움직이는지확인() {
        Knight knight = new Knight(Player.WHITE);
        Position source = Positions.of("a1");
        Position target = Positions.of("b3");
        Assertions.assertThat(knight.validateMovePosition(source, target)).isTrue();
    }
}
