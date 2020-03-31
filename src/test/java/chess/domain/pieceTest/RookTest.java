package chess.domain.pieceTest;

import chess.domain.piece.*;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Rook.of(Color.BLACK);
        assertThat(piece).isEqualTo(Rook.of(Color.BLACK));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d1", "d3", "d4", "d5", "d6", "d7", "d8", "a2", "b2", "c2", "e2", "f2", "g2", "h2"})
    @DisplayName("말의 위치(룩)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeRook(String input) {
        Piece piece = Rook.of(Color.WHITE);
        Set<Square> availableSquares = piece.calculateScope(Square.of("d2"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(14);
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

        Piece piece = Rook.of(Color.BLACK);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f6", "h6", "f3", "c3", "c8"})
    @DisplayName("rook이 갈 수 없는 칸이 갈수있는 칸 리스트에 포함 안돼있는지 확인")
    void wrongMovableRookSquareTest(String input) {
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

        Piece piece = Rook.of(Color.BLACK);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isFalse();
    }

}
