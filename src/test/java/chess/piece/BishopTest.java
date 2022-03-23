package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    @DisplayName("비숍의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Bishop bishop = new Bishop(new Position(File.A, Rank.ONE), Team.WHITE);

        assertThat(bishop.isMovable(new Position(File.F, Rank.SIX))).isTrue();
    }
}
