package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트의 진행 방향이 맞는다면 true 반환")
    void isCorrectMovable() {
        Knight knight = new Knight(Position.of('a', '1'), Team.WHITE);

        assertThat(knight.isMovable(Position.of('b', '3'))).isTrue();
    }

    @Test
    @DisplayName("나이트의 진행 방향이 틀리다면 false 반환")
    void isNotCorrectMovable() {
        Knight knight = new Knight(Position.of('a', '1'), Team.WHITE);

        assertThat(knight.isMovable(Position.of('b', '2'))).isFalse();
    }
}
