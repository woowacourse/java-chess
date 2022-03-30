package chess.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EndTest {
    @DisplayName("엔드 상태가 된 즉시 종료되는지 테스트")
    @Test
    void status() {
        assertThat(new End()).isInstanceOf(IllegalArgumentException.class);
    }
}
