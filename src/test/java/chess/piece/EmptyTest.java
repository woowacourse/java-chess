package chess.piece;

import chess.domain.piece.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {

    @Test
    @DisplayName("blank 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingEmpty() {

        assertDoesNotThrow(Empty::new);
    }


}