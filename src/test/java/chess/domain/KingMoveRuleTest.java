package chess.domain;

import chess.domain.piece.moveRule.KingMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingMoveRuleTest {
    @Test
    void 킹_움직임_실패(){
        MoveRule moveRule = new KingMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_B, Rank.RANK3);

        assertThatThrownBy(() -> moveRule.possibleRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 인접한 칸으로만 이동할 수 있습니다.");
    }
}
