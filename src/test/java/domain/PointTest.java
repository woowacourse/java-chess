package domain;

import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointTest {
    @Test
    @DisplayName("가로와 세로로 이루어진 하나의 위치를 나타낼 수 있다.")
    void create() {
        assertDoesNotThrow(() -> new Point(File.A, Rank.FIVE));
    }

    @Test
    @DisplayName("가로세로 심볼 문자열이 주어지면 해당 심볼에 대응하는 위치 객체를 반환한다.")
    void valueOf() {
        assertThat(Point.fromSymbol("f4"))
                .isEqualTo(new Point(File.F, Rank.FOUR));
    }

    @Test
    @DisplayName("체스판 위치로 표현할 수 없는 문자열이 주어지면 예외가 발생한다.")
    void invalidValueOf() {
        assertThatThrownBy(() -> Point.fromSymbol("d9"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}