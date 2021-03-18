package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @DisplayName("Game 객체 생성 : 성공")
    @Test
    void create() {
        assertThatCode(Game::new).doesNotThrowAnyException();
    }
}