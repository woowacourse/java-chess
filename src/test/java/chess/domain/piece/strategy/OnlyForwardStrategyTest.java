package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OnlyForwardStrategyTest {

    @DisplayName("첫 번째 이동 시, 수직으로 최대 두 칸까지 이동할 수 있으면 True를 리턴한다.")
    @ParameterizedTest
    @CsvSource({"TWO, e, THREE", "TWO, e, FOUR", "SEVEN, e, SIX", "SEVEN, e, FIVE"})
    void returnTrueIfPawnMoveVerticalUpToTwoStepWhenFirstMove(
            final Rank sourceRank, final File file, final Rank targetRank) {
        final MoveStrategy moveStrategy = new OnlyForwardStrategy();
        final Square source = new Square(File.e, sourceRank);
        final Square target = new Square(file, targetRank);

        final boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("두 번째 이동부터는 수직으로 한 칸씩 이동할 수 있으면 True를 리턴한다.")
    @ParameterizedTest
    @CsvSource({"THREE, e, FOUR", "SIX, e, FIVE"})
    void returnTrueIfCanMoveVerticalOnlyOneStepAfterFirstMove(
            final Rank sourceRank, final File file, final Rank targetRank) {
        final MoveStrategy moveStrategy = new OnlyForwardStrategy();
        final Square source = new Square(File.e, sourceRank);
        final Square target = new Square(file, targetRank);

        final boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("두 번째 이동부터 수직으로 두 칸 이상 이동하면 False를 리턴한다.")
    @Test
    void returnFalseIfExceedStepLimitAfterFirstMove() {
        final MoveStrategy moveStrategy = new OnlyForwardStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.SIX);

        final boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }

    @DisplayName("수직으로 이동하지 않으면 False를 리턴한다.")
    @Test
    void returnFalseWhenNotVertical() {
        final MoveStrategy moveStrategy = new OnlyForwardStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.f, Rank.FOUR);

        final boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
