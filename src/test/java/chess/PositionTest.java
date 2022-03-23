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
    void isVertical() {
    }
}
