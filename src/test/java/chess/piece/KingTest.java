package chess.piece;

import chess.domain.Color;
import chess.domain.piece.King;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    @DisplayName("color를 입력하면 King 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingKing() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new King(black)),
                () -> assertDoesNotThrow(() -> new King(white))
        );
    }


}