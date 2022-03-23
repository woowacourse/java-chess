package chess.domain;

import static chess.domain.piece.File.ONE;
import static chess.domain.piece.Rank.A;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("위치를 나타내는 Position")
    @Test
    public void position() {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> new Position(A, ONE));
    }
}
