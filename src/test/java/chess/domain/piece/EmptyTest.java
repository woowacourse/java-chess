package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmptyTest {

    @Test
    @DisplayName("빈말 테스트")
    void createTest() {
        assertThat(Empty.create()).isInstanceOf(Empty.class);
    }
}
