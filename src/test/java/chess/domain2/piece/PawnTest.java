package chess.domain2.piece;

import chess.domain2.Color;
import chess.domain2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Pawn pawn = Pawn.of(Color.BLACK);
        assertThat(pawn).isEqualTo(Pawn.of(Color.BLACK));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> Pawn.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @Test
    @DisplayName("판의 정보를 가져와서 폰이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movablePawnSquareTest() {
        Map<Square, Piece> board = new HashMap<>();

        board.put(Square.of("b5"), Knight.of(Color.BLACK));
        board.put(Square.of("e5"), Knight.of(Color.BLACK));
        board.put(Square.of("f5"), Knight.of(Color.WHITE));
        board.put(Square.of("c6"), Knight.of(Color.WHITE));
        board.put(Square.of("f5"), Knight.of(Color.WHITE));
        board.put(Square.of("f5"), Knight.of(Color.WHITE));

        Pawn pawn = Pawn.of(Color.BLACK);
        board.put(Square.of("c6"), pawn);
        Set<Square> availableSquares = pawn.getMovableSquares(Square.of("c6"), board);
        assertThat(availableSquares.contains(Square.of("c5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);

        board.remove(Square.of("c6"));
        board.put(Square.of("g6"), pawn);
        availableSquares = pawn.getMovableSquares(Square.of("g6"), board);
        assertThat(availableSquares.contains(Square.of("g5"))).isTrue();
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(2);
        board.remove(Square.of("g6"));

        board.put(Square.of("e6"), pawn);
        availableSquares = pawn.getMovableSquares(Square.of("e6"), board);
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);
    }
}
