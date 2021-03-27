package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceColorTest {

    @DisplayName("턴이 바뀔 때 플레이 가능한 유저의 색이 바뀐다.")
    @Test
    void reverse() {
        assertAll(
            () -> assertEquals(PieceColor.BLACK, PieceColor.WHITE.reversed()),
            () -> assertEquals(PieceColor.WHITE, PieceColor.BLACK.reversed())
        );
    }
}