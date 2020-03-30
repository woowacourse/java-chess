package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Queen queen = Queen.of(Color.BLACK);
        assertThat(queen).isEqualTo(Queen.of(Color.BLACK));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> Queen.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "a6", "b6", "d6", "e6", "b5", "d5", "a4", "e4", "e8"})
    @DisplayName("판의 정보를 가져와서 퀸이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableQueenSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), Queen.of(Color.WHITE));
        board.put(Square.of("c7"), Queen.of(Color.WHITE));
        board.put(Square.of("a6"), Queen.of(Color.WHITE));
        board.put(Square.of("c5"), Queen.of(Color.BLACK));
        board.put(Square.of("e8"), Queen.of(Color.WHITE));
        board.put(Square.of("f6"), Queen.of(Color.BLACK));
        board.put(Square.of("f3"), Queen.of(Color.BLACK));
        board.put(Square.of("g6"), Queen.of(Color.BLACK));
        board.put(Square.of("g2"), Queen.of(Color.WHITE));

        Queen queen = Queen.of(Color.BLACK);
        Set<Square> availableSquares = queen.getMovableSquares(Square.of("c6"), board);
        for (Square square : availableSquares) {
        }

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(12);
    }
}
