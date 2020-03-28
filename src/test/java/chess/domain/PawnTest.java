package chess.domain;

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
    @DisplayName("말의 위치(pawn)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopePawnBlack() {
        Pawn pawnBlack = Pawn.of(Color.BLACK);
        Pawn pawnWhite = Pawn.of(Color.WHITE);

        Set<Square> availableSquaresBlack = pawnBlack.calculateScope(Square.of("a7"));
        Set<Square> availableSquaresWhite = pawnWhite.calculateScope(Square.of("a6"));

        assertThat(availableSquaresBlack.contains(Square.of("a6"))).isTrue();
        assertThat(availableSquaresBlack.contains(Square.of("a5"))).isTrue();
        assertThat(availableSquaresWhite.contains(Square.of("a7"))).isTrue();

        assertThat(availableSquaresBlack.size()).isEqualTo(2);
        assertThat(availableSquaresWhite.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("판의 정보를 가져와서 폰이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movablePawnSquareTest() {
        Map<Square, Piece> board = new HashMap<>();

        board.put(Square.of("b5"), Knight.of(Color.BLACK));
        board.put(Square.of("e5"), Knight.of(Color.BLACK));
        board.put(Square.of("f5"), Knight.of(Color.WHITE));

        Pawn pawn = Pawn.of(Color.BLACK);
        Set<Square> availableSquares = pawn.calculateMoveBoundary(Square.of("c6"), board);
        assertThat(availableSquares.contains(Square.of("c5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = pawn.calculateMoveBoundary(Square.of("e6"), board);
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = pawn.calculateMoveBoundary(Square.of("g6"), board);
        assertThat(availableSquares.contains(Square.of("g5"))).isTrue();
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(2);
    }
}
