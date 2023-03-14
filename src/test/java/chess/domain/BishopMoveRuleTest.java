package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopMoveRuleTest {
    @Test
    void 비숍_움직임_실패() {
        MoveRule moveRule = new BishopMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_B, Rank.RANK1);

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍은 대각선상으로만 움직일 수 있습니다.");
    }

    @Test
    void 비숍_움직임_성공() {
        MoveRule moveRule = new BishopMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_H, Rank.RANK8);

        assertThat(moveRule.move(currentPosition, nextPosition)).isEqualTo(nextPosition);
    }
}
