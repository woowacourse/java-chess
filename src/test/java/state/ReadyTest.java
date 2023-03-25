package state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("다음 차례는 화이트이다")
    @Test
    void next() {
        //given
        final Ready ready = new Ready();

        //when

        //then
        assertThat(ready.next()).isInstanceOf(White.class);
    }
}
