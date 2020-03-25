import chess.domain.chesspieces.King;
import chess.domain.moverules.MoveRule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KingTest {

    @Test
    void kingMoveRulesTest() {
        King king = new King("k");
        List<MoveRule> moveRules = king.getMoveRules();

        Assertions.assertThat(moveRules.contains(MoveRule.DIAGONAL_DOWN_LEFT)).isTrue();
    }
}
