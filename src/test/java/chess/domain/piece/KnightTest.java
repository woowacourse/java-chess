package chess.domain.piece;


import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KnightTest {

    @DisplayName("한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"b2", "c1", "b4", "c5", "f4", "e5", "f2", "e1"})
    void canMove(String position) {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Position("d3"), new Position(position), null)).isTrue();
    }

    @DisplayName("한 단위 이상 움직일 수 없다")
    @Test
    void canNotMove_MoreThanOneUnit() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Position("d3"), new Position("f7"), null)).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "c3", "b1", "b3"})
    void canNotMove(String position) {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Position("d3"), new Position(position), null)).isFalse();

    }
}
