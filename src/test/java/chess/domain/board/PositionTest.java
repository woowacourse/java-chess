package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

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
}
