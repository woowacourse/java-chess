package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는지 확인하는 테스트")
    void move() {
        Rook rook = new Rook(new Position(File.A, Rank.ONE), Team.WHITE);

        assertDoesNotThrow(
                () -> rook.move(new Position(File.F, Rank.ONE))
        );
    }
}
