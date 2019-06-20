package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static chess.domain.Direction.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigatorTest {
    private static List<Direction> directions;

    @BeforeEach
    void setUp() {
        directions = Arrays.stream(Direction.values()).collect(Collectors.toList());
    }

    @Test
    void 동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c2"));
        assertThat(navigator.getDirection(directions)).isEqualTo(EAST);
    }

    @Test
    void 서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a2"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.WEST);
    }

    @Test
    void 남쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("b1"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.SOUTH);
    }

    @Test
    void 북쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("b3"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.NORTH);
    }

    @Test
    void 북동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c3"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.NORTH_EAST);
    }

    @Test
    void 남동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c1"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    void 남서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a1"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.SOUTH_WEST);
    }

    @Test
    void 북서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a3"));
        assertThat(navigator.getDirection(directions)).isEqualTo(Direction.NORTH_WEST);
    }
}
