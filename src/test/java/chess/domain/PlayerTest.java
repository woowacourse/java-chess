package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("Player 객체 생성 성공")
    @Test
    void create() {
        assertAll(
                () -> assertThatCode(() -> new Player(PieceColor.WHITE)).doesNotThrowAnyException(),
                () -> assertThatCode(() -> new Player(PieceColor.BLACK)).doesNotThrowAnyException()
        );
    }
}