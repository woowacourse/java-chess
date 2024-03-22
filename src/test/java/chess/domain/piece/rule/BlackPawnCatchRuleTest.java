package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackPawnCatchRuleTest {

    @Test
    @DisplayName("검정폰 잡기 규칙 준수 테스트(성공)")
    void should_obey_rule_when_right_condition() {
        FileDifference fileDifference = new FileDifference(1);
        RankDifference rankDifference = new RankDifference(-1);

        BlackPawnCatchRule rule = BlackPawnCatchRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isTrue();
    }

    @Test
    @DisplayName("검정폰 잡기 규칙 준수 테스트(실패)")
    void should_not_obey_rule_when_wrong_condition() {
        FileDifference fileDifference = new FileDifference(0);
        RankDifference rankDifference = new RankDifference(-1);

        BlackPawnCatchRule rule = BlackPawnCatchRule.instance();

        assertThat(rule.obey(fileDifference, rankDifference)).isFalse();
    }
}
