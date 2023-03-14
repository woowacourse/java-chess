package chess.piece.board;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("File과 Rank를 받아서 Position을 생성한다.")
    void generatePosition() {
        Assertions.assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }
}
