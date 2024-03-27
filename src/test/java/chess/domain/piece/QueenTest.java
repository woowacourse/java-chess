package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"D, 8", "D, 1", "A, 4", "H, 4", "F, 6", "B, 2", "F, 2", "B, 6"})
    @DisplayName("퀸은 상하좌우 및 대각선 방향으로 이동할 수 있다.")
    void queenMoveTest(String file, int rank) {
        // given
        Queen queen = new Queen(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = queen.isMovable(source, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();
    }
}
