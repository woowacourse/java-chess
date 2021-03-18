package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {
    @DisplayName("보드 초기화")
    @Test
    void init() {
        assertThatCode(BoardInitializer::init).doesNotThrowAnyException();
    }
}