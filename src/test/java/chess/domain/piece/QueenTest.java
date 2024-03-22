package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸은 상하좌우 및 대각선 방향으로 이동할 수 있다.")
    void queenMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(queen.isMovable(source, Position.of(File.D, Rank.EIGHT))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.D, Rank.ONE))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.A, Rank.FOUR))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.H, Rank.FOUR))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.F, Rank.SIX))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.B, Rank.TWO))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.F, Rank.TWO))).isTrue(),
                () -> assertThat(queen.isMovable(source, Position.of(File.B, Rank.SIX))).isTrue()
        );
    }
}
