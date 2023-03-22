package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RookTest {

    @DisplayName("가로/세로 여러 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "b3", "d4", "d5", "e3", "f3", "d2", "d1"})
    void canMove_HorizontalVertical_Infinite(String position) {
        Rook rook = new Rook(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(rook.isValidMove(move, null)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"c4", "b2"})
    void canNotMove(String position) {
        Rook rook = new Rook(WHITE);
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(rook.isValidMove(move, null)).isFalse();
    }
}
