package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigatorTest {
    @Test
    void 동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c2"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    void 서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a2"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    void 남쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("b1"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    void 북쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("b3"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    void 북동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c3"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.NORTH_EAST);
    }

    @Test
    void 남동쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("c1"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    void 남서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a1"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.SOUTH_WEST);
    }

    @Test
    void 북서쪽_방향_확인() {
        Navigator navigator = new Navigator(new Point("b2"),new Point("a3"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.NORTH_WEST);
    }
}
