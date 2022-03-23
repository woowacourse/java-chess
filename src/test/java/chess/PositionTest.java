package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    @DisplayName("position끼리 수평이면 true를 반환한다.")
    void isHorizontal() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.D, Rank.ONE);

        assertThat(source.isHorizontal(target)).isTrue();
    }

    @Test
    @DisplayName("position끼리 수직이면 true를 반환한다.")
    void isVertical() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.A, Rank.THREE);

        assertThat(source.isVertical(target)).isTrue();
    }

    @Test
    @DisplayName("position끼리 대각선이면 true를 반환한다.")
    void isDiagonal() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.F, Rank.SIX);

        assertThat(source.isDiagonal(target)).isTrue();
    }
}
