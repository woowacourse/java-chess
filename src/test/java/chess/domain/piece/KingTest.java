package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 상하좌우 및 대각선 방향으로 한 칸 이동할 수 있다.")
    void kingMoveTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(king.isMovable(source, Position.of(File.D, Rank.FIVE))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.E, Rank.FIVE))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.E, Rank.FOUR))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.E, Rank.THREE))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.D, Rank.THREE))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.C, Rank.THREE))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.C, Rank.FOUR))).isTrue(),
                () -> assertThat(king.isMovable(source, Position.of(File.C, Rank.FIVE))).isTrue()
        );
    }

    @Test
    @DisplayName("킹은 한 번에 여러 칸 이동할 수 없다.")
    void kingMaxUnitTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when,
        boolean movable = king.isMovable(source, Position.of(File.D, Rank.SIX));
        // then
        assertThat(movable).isFalse();
    }
}
