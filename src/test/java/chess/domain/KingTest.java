package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        King king = King.of(Color.BLACK);
        assertThat(king).isEqualTo(King.of(Color.BLACK));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> King.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"e6", "e8", "f6", "f7", "f8", "d6", "d7", "d8"})
    @DisplayName("말의 위치(king)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKing(String input) {
        King king = King.of(Color.BLACK);
        Set<Square> availableSquares = king.calculateScope(Square.of("e7"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "b5", "b7"})
    @DisplayName("판의 정보를 가져와서 king이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKingSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("a5"), Pawn.of(Color.WHITE));
        board.put(Square.of("b6"), Pawn.of(Color.BLACK));
        King king = King.of(Color.BLACK);
        Set<Square> availableSquares = king.calculateMoveBoundary(Square.of("a6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }
}
