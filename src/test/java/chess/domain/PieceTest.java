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

    @ParameterizedTest
    @ValueSource(strings = {"c4", "d5", "d1", "f1", "f5", "g2"})
    @DisplayName("판의 정보를 가져와서 나이트가 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKnightSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("d5"), Piece.of(Color.BLACK, Type.KING));
        board.put(Square.of("c2"), Piece.of(Color.WHITE, Type.QUEEN));
        board.put(Square.of("g4"), Piece.of(Color.WHITE, Type.PAWN));
        Piece piece = Piece.of(Color.WHITE, Type.KNIGHT);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("e3"), board);
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("판의 정보를 가져와서 폰이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movablePawnSquareTest() {
        Map<Square, Piece> board = new HashMap<>();

        board.put(Square.of("b5"), Piece.of(Color.BLACK, Type.KNIGHT));
        board.put(Square.of("e5"), Piece.of(Color.BLACK, Type.KNIGHT));
        board.put(Square.of("f5"), Piece.of(Color.WHITE, Type.KNIGHT));

        Piece piece = Piece.of(Color.BLACK, Type.PAWN);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);
        assertThat(availableSquares.contains(Square.of("c5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = piece.calculateMoveBoundary(Square.of("e6"), board);
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = piece.calculateMoveBoundary(Square.of("g6"), board);
        assertThat(availableSquares.contains(Square.of("g5"))).isTrue();
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "a6", "b6", "d6", "e6", "b5", "d5", "a4", "e4", "e8"})
    @DisplayName("판의 정보를 가져와서 퀸이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableQueenSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("c7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("a6"), Piece.of(Color.WHITE, Type.KING));
        board.put(Square.of("c5"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("e8"), Piece.of(Color.WHITE, Type.KNIGHT));
        board.put(Square.of("f6"), Piece.of(Color.BLACK, Type.QUEEN));
        board.put(Square.of("f3"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("g6"), Piece.of(Color.BLACK, Type.KING));
        board.put(Square.of("g2"), Piece.of(Color.WHITE, Type.PAWN));

        Piece piece = Piece.of(Color.BLACK, Type.QUEEN);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(12);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "d7", "b5", "d5", "a4", "e4", "e8"})
    @DisplayName("판의 정보를 가져와서 bishop이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableBishopSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("c7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("a6"), Piece.of(Color.WHITE, Type.KING));
        board.put(Square.of("c5"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("e8"), Piece.of(Color.WHITE, Type.KNIGHT));
        board.put(Square.of("f6"), Piece.of(Color.BLACK, Type.QUEEN));
        board.put(Square.of("f3"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("g6"), Piece.of(Color.BLACK, Type.KING));
        board.put(Square.of("g2"), Piece.of(Color.WHITE, Type.PAWN));

        Piece piece = Piece.of(Color.BLACK, Type.BISHOP);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a6", "b6", "d6", "e6", "c7"})
    @DisplayName("판의 정보를 가져와서 rook이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableRookSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("c7"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("a6"), Piece.of(Color.WHITE, Type.KING));
        board.put(Square.of("c5"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("e8"), Piece.of(Color.WHITE, Type.KNIGHT));
        board.put(Square.of("f6"), Piece.of(Color.BLACK, Type.QUEEN));
        board.put(Square.of("f3"), Piece.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("g6"), Piece.of(Color.BLACK, Type.KING));
        board.put(Square.of("g2"), Piece.of(Color.WHITE, Type.PAWN));

        Piece piece = Piece.of(Color.BLACK, Type.ROOK);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "b5", "b7"})
    @DisplayName("판의 정보를 가져와서 king이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKingSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("a5"), Piece.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("b6"), Piece.of(Color.BLACK, Type.PAWN));
        Piece piece = Piece.of(Color.BLACK, Type.KING);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("a6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("체스 말이 블랙인지 검증하는 테스트")
    void isBlack() {
        assertThat(Piece.of(Color.BLACK, Type.PAWN).isBlack()).isTrue();
        assertThat(Piece.of(Color.WHITE, Type.KING).isBlack()).isFalse();
    }
}
