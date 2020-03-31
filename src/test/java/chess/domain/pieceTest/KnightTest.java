package chess.domain.pieceTest;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Knight.of(Color.BLACK);
        assertThat(piece).isEqualTo(Knight.of(Color.BLACK));
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "a2", "b5", "a4", "e4", "d1", "d5", "e2"})
    @DisplayName("말의 위치(knight)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKnight(String input) {
        Piece piece = Knight.of(Color.BLACK);
        Set<Square> availableSquares = piece.calculateScope(Square.of("c3"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c3", "d4", "f4", "g1"})
    @DisplayName("판의 정보를 가져와서 나이트가 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKnightSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("d4"), King.of(Color.BLACK));
        board.put(Square.of("c1"), King.of(Color.WHITE));
        board.put(Square.of("g3"), King.of(Color.WHITE));
        Piece piece = Knight.of(Color.WHITE);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("e2"), board);
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(4);
    }
}
