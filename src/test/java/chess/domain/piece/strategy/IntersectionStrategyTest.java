package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class IntersectionStrategyTest {

    @DisplayName("모든 이동 전략 조건을 만족하면 True를 리턴한다.")
    @Test
    void returnTrueWhenAllConditionsAreTrue() {
        final MoveStrategy moveStrategy = new IntersectionStrategy(
                List.of(new StraightStrategy(), new WithinOneStepStrategy())
        );
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.d, Rank.FOUR);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("이동 전략 조건 중 하나라도 불만족하면 False를 리턴한다.")
    @Test
    void returnFalseWhenAllConditionsAreNotTrue() {
        final MoveStrategy moveStrategy = new IntersectionStrategy(
                List.of(new StraightStrategy(), new WithinOneStepStrategy())
        );
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.c, Rank.FOUR);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
