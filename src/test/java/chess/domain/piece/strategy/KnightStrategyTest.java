package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightStrategyTest {

    @DisplayName("열 한 칸, 행 두 칸을 이동할 수 있으면 True를 리턴한다.")
    @Test
    void returnTrueWhenCanMoveFileOneStepAndRankTwoStep() {
        final MoveStrategy moveStrategy = new KnightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.f, Rank.SIX);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("열 두 칸, 행 한 칸을 이동할 수 있으면 True를 리턴한다.")
    @Test
    void returnTrueWhenCanMoveFileTwoStepAndRankOneStep() {
        final MoveStrategy moveStrategy = new KnightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.c, Rank.FIVE);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("나이트가 이동할 수 없으면 False를 리턴한다.")
    @Test
    void returnFalseWhenKnightCannotMove() {
        final MoveStrategy moveStrategy = new KnightStrategy();
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.FIVE);

        boolean actual = moveStrategy.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
