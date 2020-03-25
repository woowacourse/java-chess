package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PieceTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Piece.of(Color.BLACK, Type.KING);
        assertThat(piece).isEqualTo(Piece.of(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> Piece.of(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b1", "d1", "e1", "f1", "g1", "h1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "d2", "e3", "f4", "g5", "h6", "b2", "a3"})
    @DisplayName("말의 위치(퀸)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeQueen(String input) {
        Piece piece = Piece.of(Color.WHITE, Type.QUEEN);
        Set<Square> availableSquares = piece.calculateScope(Square.of("c1"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(21);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c1", "e1", "c3", "b4", "a5", "e3", "f4", "g5", "h6"})
    @DisplayName("말의 위치(비숍)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeBishop(String input) {
        Piece piece = Piece.of(Color.WHITE, Type.BISHOP);
        Set<Square> availableSquares = piece.calculateScope(Square.of("d2"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(9);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d1", "d3", "d4", "d5", "d6", "d7", "d8", "a2", "b2", "c2", "e2", "f2", "g2", "h2"})
    @DisplayName("말의 위치(룩)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeRook(String input) {
        Piece piece = Piece.of(Color.WHITE, Type.ROOK);
        Set<Square> availableSquares = piece.calculateScope(Square.of("d2"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(14);
    }

    @ParameterizedTest
    @ValueSource(strings = {"e6", "e8", "f6", "f7", "f8", "d6", "d7", "d8"})
    @DisplayName("말의 위치(king)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKing(String input) {
        Piece piece = Piece.of(Color.BLACK, Type.KING);
        Set<Square> availableSquares = piece.calculateScope(Square.of("e7"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "a2", "b5", "a4", "e4", "d1", "d5", "e2"})
    @DisplayName("말의 위치(knight)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKnight(String input) {
        Piece piece = Piece.of(Color.BLACK, Type.KNIGHT);
        Set<Square> availableSquares = piece.calculateScope(Square.of("c3"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(8);
    }

    @Test
    @DisplayName("말의 위치(pawn)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopePawnBlack() {
        Piece pieceBlack = Piece.of(Color.BLACK, Type.PAWN);
        Piece pieceWhite = Piece.of(Color.WHITE, Type.PAWN);

        Set<Square> availableSquaresBlack = pieceBlack.calculateScope(Square.of("a7"));
        Set<Square> availableSquaresWhite = pieceWhite.calculateScope(Square.of("a6"));

        assertThat(availableSquaresBlack.contains(Square.of("a6"))).isTrue();
        assertThat(availableSquaresBlack.contains(Square.of("a5"))).isTrue();
        assertThat(availableSquaresWhite.contains(Square.of("a7"))).isTrue();

        assertThat(availableSquaresBlack.size()).isEqualTo(2);
        assertThat(availableSquaresWhite.size()).isEqualTo(1);
    }
}
