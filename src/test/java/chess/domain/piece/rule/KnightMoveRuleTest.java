package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveRuleTest {

    @Test
    @DisplayName("나이트 이동 규칙 준수 테스트(성공)")
    void should_obey_rule_when_right_condition() {
        FileDifference fileDifference = new FileDifference(2);
        RankDifference rankDifference = new RankDifference(1);

        KnightMoveRule rule = KnightMoveRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isTrue();
    }

    @Test
    @DisplayName("나이트 이동 규칙 준수 테스트(실패)")
    void should_not_obey_rule_when_wrong_condition() {
        FileDifference fileDifference = new FileDifference(4);
        RankDifference rankDifference = new RankDifference(4);

        KnightMoveRule rule = KnightMoveRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isFalse();
    }
}
