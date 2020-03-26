import chess.domain.Player;
import chess.domain.chesspieces.King;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    private final King king = new King(Player.WHITE);

    @DisplayName("King: 이동 가능한 방향 확인")
    @Test
    void kingMoveRulesTest() {
        List<Direction> directions = king.getDirections();
        Assertions.assertThat(directions.contains(Direction.DIAGONAL_DOWN_LEFT)).isTrue();
    }

    @DisplayName("이동 확인: 정상")
    @Test
    void test() {
        Position source = Positions.of("a1");
        Position target = Positions.of("a2");
        assertThat(king.movable(source, target)).isTrue();
    }

    @DisplayName("이동 확인: (예외) 2칸 이동")
    @Test
    void test2() {
        Position source = Positions.of("a1");
        Position target = Positions.of("a3");
        assertThat(king.movable(source, target)).isFalse();
    }
}
