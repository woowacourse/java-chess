package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        Assertions.assertThatCode(() -> new ChessBoard())
                .doesNotThrowAnyException();
    }
}
