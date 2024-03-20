package model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("미리 생성된 Position를 제외한 Position을 요구하였을 때, 예외를 던진다")
    void of_ShouldThrowException_WhenInputInvalidFileAndRank() {
        assertThrows(IllegalArgumentException.class, () -> Position.of(9, 9));
    }

    @Test
    @DisplayName("전달 받은 위치가 체스판 위에 존재하는 위치인지 판별한다.")
    void isAvailablePosition_ShouldReturnTrue_WhenAvailableFileAndRank() {
        Position position = Position.of(1, 1);

        assertTrue(position.isAvailablePosition(1, 1));
    }

    @Test
    @DisplayName("전달 받은 위치가 체스판 위에 존재하는 위치인지 판별한다.")
    void isAvailablePosition_ShouldReturnFalse_WhenNotAvailableFileAndRank() {
        Position position = Position.of(1, 1);
        assertAll(() -> {
            assertTrue(position.isAvailablePosition(0, 0));
            assertTrue(position.isAvailablePosition(9, 0));
            assertTrue(position.isAvailablePosition(9, 9));
            assertTrue(position.isAvailablePosition(0, 9));
        });
    }
}
