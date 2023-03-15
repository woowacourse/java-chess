package chess.domain.piece.moveRule;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMoveRuleTest {

    @Test
    void 나이트_움직임_실패() {
        MoveRule moveRule = new KnightMoveRule();
        Position currentPosition = Position.of(File.FILE_B, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_B, Rank.RANK2);

        assertThatThrownBy(() -> moveRule.possibleRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 L 모양으로만 움직일 수 있습니다.");
    }

}
