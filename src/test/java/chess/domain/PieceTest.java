package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceImpl;
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
        Piece piece = PieceImpl.of(Color.BLACK, Type.KING);
        assertThat(piece).isEqualTo(PieceImpl.of(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> PieceImpl.of(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d1", "d3", "d4", "d5", "d6", "d7", "d8", "a2", "b2", "c2", "e2", "f2", "g2", "h2"})
    @DisplayName("말의 위치(룩)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeRook(String input) {
        Piece piece = PieceImpl.of(Color.WHITE, Type.ROOK);
        Set<Square> availableSquares = piece.calculateScope(Square.of("d2"));
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(14);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a6", "b6", "d6", "e6", "c7"})
    @DisplayName("판의 정보를 가져와서 rook이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableRookSquareTest(String input) {
        Map<Square, Piece> board = new HashMap<>();
        board.put(Square.of("b7"), PieceImpl.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("c7"), PieceImpl.of(Color.WHITE, Type.PAWN));
        board.put(Square.of("a6"), PieceImpl.of(Color.WHITE, Type.KING));
        board.put(Square.of("c5"), PieceImpl.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("e8"), PieceImpl.of(Color.WHITE, Type.KNIGHT));
        board.put(Square.of("f6"), PieceImpl.of(Color.BLACK, Type.QUEEN));
        board.put(Square.of("f3"), PieceImpl.of(Color.BLACK, Type.PAWN));
        board.put(Square.of("g6"), PieceImpl.of(Color.BLACK, Type.KING));
        board.put(Square.of("g2"), PieceImpl.of(Color.WHITE, Type.PAWN));

        Piece piece = PieceImpl.of(Color.BLACK, Type.ROOK);
        Set<Square> availableSquares = piece.calculateMoveBoundary(Square.of("c6"), board);

        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("체스 말이 블랙인지 검증하는 테스트")
    void isBlack() {
        assertThat(PieceImpl.of(Color.BLACK, Type.PAWN).isBlack()).isTrue();
        assertThat(PieceImpl.of(Color.WHITE, Type.KING).isBlack()).isFalse();
    }
}
