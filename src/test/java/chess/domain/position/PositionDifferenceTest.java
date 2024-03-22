package chess.domain.position;

import chess.domain.piece.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionDifferenceTest {

    @Test
    @DisplayName("규칙 준수 테스트(성공)")
    void should_determine_obey_rule_when_obey() {
        PositionDifference positionDifference = new PositionDifference(new FileDifference(1), new RankDifference(2));
        Rule rule = (fileDifference, rankDifference) -> fileDifference.hasDistance(1) && rankDifference.hasDistance(2);

        assertThat(positionDifference.isObeyRule(rule)).isTrue();
    }

    @Test
    @DisplayName("규칙 준수 테스트(실패)")
    void should_determine_obey_rule_when_not_obey() {
        PositionDifference positionDifference = new PositionDifference(new FileDifference(1), new RankDifference(2));
        Rule rule = (fileDifference, rankDifference) -> fileDifference.hasDistance(3) && rankDifference.hasDistance(2);

        assertThat(positionDifference.isObeyRule(rule)).isFalse();
    }
}
