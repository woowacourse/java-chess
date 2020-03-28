package chess.domain.pieceTest;

import chess.domain.Color;
import chess.domain.Square;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Queen.of(Color.BLACK);
        assertThat(piece).isEqualTo(Queen.of(Color.BLACK));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b1", "d1", "e1", "f1", "g1", "h1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "d2", "e3", "f4", "g5", "h6", "b2", "a3"})
    @DisplayName("말의 위치(퀸)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeQueen(String input) {
        Piece piece = Queen.of(Color.WHITE);
        Set<Square> availableSquares = piece.calculateScope(Square.of("c1"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(21);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "a6", "b6", "d6", "e6", "b5", "d5", "a4", "e4", "e8"})
    @DisplayName("판의 정보를 가져와서 퀸이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableQueenSquareTest(String input) {
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

        Piece piece = Queen.of(Color.BLACK);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(12);
    }
}
