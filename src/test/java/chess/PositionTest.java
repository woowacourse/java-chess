package chess;

import static chess.File.ONE;
import static chess.Rank.A;

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
