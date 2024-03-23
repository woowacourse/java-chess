package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampTest {

    @DisplayName("현재 Camp가 WHITE 면 BLACK을 현재 Camp가 BLACK이면 WHITE를 반환한다.")
    @Test
    void checkToggle() {
        assertAll(
                () -> assertThat(Camp.BLACK.toggle()).isEqualTo(Camp.WHITE),
                () -> assertThat(Camp.WHITE.toggle()).isEqualTo(Camp.BLACK)
        );
    }
}
