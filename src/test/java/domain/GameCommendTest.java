package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameCommendTest {

    @Test
    void check_input() {
        assertThat(GameCommend.answer("start")).isEqualTo(GameCommend.START);
    }
}
