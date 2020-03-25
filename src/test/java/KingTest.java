import chess.domain.chesspieces.King;
import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @DisplayName("moveRules Test : 움직일 수 있는 방향을 가지고 있는지에 테스트")
    @Test
    void kingMoveRulesTest() {
        King king = new King("k");
        List<MoveRule> moveRules = king.getMoveRules();
        Assertions.assertThat(moveRules.contains(MoveRule.DIAGONAL_DOWN_LEFT)).isTrue();
    }

    @DisplayName("movable test: 정상 범위로 입력했을 때 ")
    @Test
    void test() {
        Position source = Positions.of("a1");
        Position target = Positions.of("a2");
        King king = new King("k");
        assertThat(king.movable(source, target)).isTrue();
    }

    @DisplayName("두칸 이상 움직였을때 예외처리")
    @Test
    void test2() {
        Position source = Positions.of("a1");
        Position target = Positions.of("a3");
        King king = new King("k");
        assertThat(king.movable(source, target)).isFalse();
    }
}
