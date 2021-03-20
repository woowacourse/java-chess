package chess.domain;

import chess.domain.utils.BoardInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BoardInitializerTest {
    @DisplayName("보드 초기화")
    @Test
    void init() {
        assertThatCode(BoardInitializer::init).doesNotThrowAnyException();
    }
}