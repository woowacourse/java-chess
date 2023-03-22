package domain;

import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointTest {
    @Test
    @DisplayName("가로와 세로로 이루어진 하나의 위치를 나타낼 수 있다.")
    void create() {
        assertDoesNotThrow(() -> new Point(File.A, Rank.FIVE));
    }
}