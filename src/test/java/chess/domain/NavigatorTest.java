package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigatorTest {
    @Test
    void 방향_확인() {
        Navigator navigator = new Navigator(new Point("a1"),new Point("a2"));
        assertThat(navigator.getDirection()).isEqualTo(Direction.NORTH);
    }
}
