package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트의 진행 방향이 맞는다면 true 반환")
    void isCorrectMovable() {
        Knight knight = new Knight(new Position(File.A, Rank.ONE), Team.WHITE);

        assertThat(knight.isMovable(new Position(File.B, Rank.THREE))).isTrue();
    }

    @Test
    @DisplayName("나이트의 진행 방향이 틀리다면 false 반환")
    void isNotCorrectMovable() {
        Knight knight = new Knight(new Position(File.A, Rank.ONE), Team.WHITE);

        assertThat(knight.isMovable(new Position(File.B, Rank.TWO))).isFalse();
    }
}
