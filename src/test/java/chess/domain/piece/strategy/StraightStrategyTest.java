package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StraightStrategyTest {

    @DisplayName("출발지에서 목적지까지 수직으로 이동할 수 있으면 True를 리턴한다.")
    @ParameterizedTest
    @CsvSource({"ONE", "EIGHT"})
    void returnTrueWhenCanMoveVertical(final Rank rank) {
        final MoveStrategy moveStrategy = new StraightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, rank);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 수평으로 이동할 수 있으면 True를 리턴한다.")
    @ParameterizedTest
    @CsvSource({"a", "h"})
    void returnTrueWhenCanMoveHorizontal(final File file) {
        final MoveStrategy moveStrategy = new StraightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(file, Rank.FOUR);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 수평,수직 모두 이동할 수 없으면 False를 리턴한다.")
    @Test
    void returnFalseWhenCannotMoveStraight() {
        final MoveStrategy moveStrategy = new StraightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.f, Rank.FIVE);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
