package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Rook rook = new Rook(new Position(File.A, Rank.ONE), Team.WHITE);

        assertThat(rook.isMovable(new Position(File.F, Rank.ONE))).isTrue();
    }
}
