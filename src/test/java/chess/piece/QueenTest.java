package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QueenTest {

    @Test
    @DisplayName("퀸의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Queen queen = new Queen(new Position(File.A, Rank.ONE), Team.WHITE);

        assertAll(
                () -> assertThat(queen.isMovable(new Position(File.F, Rank.SIX))).isTrue(),
                () -> assertThat(queen.isMovable(new Position(File.A, Rank.FIVE))).isTrue(),
                () -> assertThat(queen.isMovable(new Position(File.F, Rank.ONE))).isTrue()
        );
    }
}
