package chess.domain;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PositionTest {

    @DisplayName("")
    @Test
    void construct() {
        assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }
}
