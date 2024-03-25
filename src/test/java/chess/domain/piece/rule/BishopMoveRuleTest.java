package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopMoveRuleTest {

    @Test
    @DisplayName("비숍 이동 규칙 준수 테스트(성공)")
    void should_obey_rule_when_right_condition() {
        FileDifference fileDifference = new FileDifference(5);
        RankDifference rankDifference = new RankDifference(5);

        BishopMoveRule rule = BishopMoveRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isTrue();
    }

    @Test
    @DisplayName("비숍 이동 규칙 준수 테스트(실패)")
    void should_not_obey_rule_when_wrong_condition() {
        FileDifference fileDifference = new FileDifference(0);
        RankDifference rankDifference = new RankDifference(4);

        BishopMoveRule rule = BishopMoveRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isFalse();
    }
}
