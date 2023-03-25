package state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndTest {

    @DisplayName("플레이 상태가 아니다")
    @Test
    void isPlaying() {
        //given
        final End end = new End();

        //when

        //then
        assertThat(end.isPlaying()).isFalse();
    }

}
