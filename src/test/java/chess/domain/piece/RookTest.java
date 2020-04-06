package chess.domain.piece;

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

public class RookTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Rook rook = Rook.of(Color.BLACK);
        assertThat(rook).isEqualTo(Rook.of(Color.BLACK));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> Rook.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a6", "b6", "d6", "e6", "c7"})
    @DisplayName("판의 정보를 가져와서 rook이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableRookSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), Pawn.of(Color.WHITE));
        board.put(Square.of("c7"), Pawn.of(Color.WHITE));
        board.put(Square.of("a6"), King.of(Color.WHITE));
        board.put(Square.of("c5"), Pawn.of(Color.BLACK));
        board.put(Square.of("e8"), Knight.of(Color.WHITE));
        board.put(Square.of("f6"), Queen.of(Color.BLACK));
        board.put(Square.of("f3"), Pawn.of(Color.BLACK));
        board.put(Square.of("g6"), King.of(Color.BLACK));
        board.put(Square.of("g2"), Pawn.of(Color.WHITE));
        board.put(Square.of("c6"), Rook.of(Color.BLACK));

        Rook rook = Rook.of(Color.BLACK);
        Set<Square> availableSquares = rook.findMovable(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }
}