package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookMoveRuleTest {

    @Test
    void 룩_무브_실패() {
        MoveRule moveRule = new RookMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_B, Rank.RANK2);

        assertThatThrownBy(() -> moveRule.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩은 직선상으로만 움직일 수 있습니다.");
    }

    @Test
    void 룩_무브_성공() {
        MoveRule moveRule = new RookMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_A, Rank.RANK2);

        assertThat(moveRule.move(currentPosition, nextPosition)).isEqualTo(nextPosition);
    }
}