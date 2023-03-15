package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    public static final Position B4 = new Position(File.B, Rank.FOUR);
    public static final Position E7 = new Position(File.E, Rank.SEVEN);

    @Test
    void isFileEquals_true() {
        var source = new Position(File.D, Rank.FIVE);
        var target = new Position(File.D, Rank.EIGHT);

        assertThat(source.isFileEquals(target)).isTrue();
    }

    @Test
    void isFileEquals_false() {
        var source = new Position(File.C, Rank.FIVE);
        var target = new Position(File.D, Rank.EIGHT);

        assertThat(source.isFileEquals(target)).isFalse();
    }

    @Test
    void isRankEqauls_True() {
        var source = new Position(File.C, Rank.EIGHT);
        var target = new Position(File.D, Rank.EIGHT);

        assertThat(source.isRankEquals(target)).isTrue();
    }

    @Test
    void isRankEqauls_False() {
        var source = new Position(File.C, Rank.FIVE);
        var target = new Position(File.D, Rank.EIGHT);

        assertThat(source.isRankEquals(target)).isFalse();
    }

    @Test
    void computeInclination_result1() {
        var source = B4;
        var target = E7;

        double v = source.computeInclination(target);

        assertThat(v).isEqualTo(1.0d);
    }

    @Test
    void computeInclination_resultNegative1() {
        var source = new Position(File.C, Rank.FIVE);
        var target = new Position(File.A, Rank.SEVEN);

        double v = source.computeInclination(target);

        assertThat(v).isEqualTo(-1.0d);
    }
}
