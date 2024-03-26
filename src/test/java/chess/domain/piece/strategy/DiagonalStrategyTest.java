package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiagonalStrategyTest {

    @DisplayName("출발지에서 목적지까지 대각선으로 이동할 수 있으면 True를 리턴한다.")
    @ParameterizedTest
    @CsvSource({"a, EIGHT", "h, SEVEN", "b, ONE", "h, ONE"})
    void returnTrueWhenCanMoveDiagonal(final File file, final Rank rank) {
        final MoveStrategy moveStrategy = new DiagonalStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(file, rank);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동할 수 없으면 False를 리턴한다.")
    @Test
    void returnFalseWhenCannotMoveDiagonal() {
        final MoveStrategy moveStrategy = new DiagonalStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.FIVE);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
