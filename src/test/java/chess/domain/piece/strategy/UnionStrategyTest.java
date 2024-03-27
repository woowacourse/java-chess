package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class UnionStrategyTest {

    @DisplayName("이동 전략 조건 중 하나만 만족하면 True를 리턴한다.")
    @Test
    void returnTrueWhenOneOfConditionsIsTrue() {
        final MoveStrategy moveStrategy = new UnionStrategy(
                List.of(new StraightStrategy(), new DiagonalStrategy())
        );
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.d, Rank.FOUR);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("이동 전략 조건 모두 불만족하면 False를 리턴한다.")
    @Test
    void returnFalseWhenAllConditionsAreTrue() {
        final MoveStrategy moveStrategy = new UnionStrategy(
                List.of(new StraightStrategy(), new DiagonalStrategy())
        );
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.f, Rank.SIX);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
