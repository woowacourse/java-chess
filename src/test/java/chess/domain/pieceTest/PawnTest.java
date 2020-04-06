package chess.domain.pieceTest;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Pawn.of(Color.BLACK);
        assertThat(piece).isEqualTo(Pawn.of(Color.BLACK));
    }

    @Test
    @DisplayName("말의 위치(pawn)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopePawnBlack() {
        Piece pieceBlack = Pawn.of(Color.BLACK);
        Piece pieceWhite = Pawn.of(Color.WHITE);

        Set<Square> squareScopeBlack = pieceBlack.calculateScope(Square.of("a7"));
        Set<Square> squareScopeWhite = pieceWhite.calculateScope(Square.of("a6"));

        System.out.println("white:" + squareScopeWhite);
        System.out.println("black:" + squareScopeBlack);
        assertThat(squareScopeBlack.contains(Square.of("a6"))).isTrue();
        assertThat(squareScopeBlack.contains(Square.of("a5"))).isTrue();

        assertThat(squareScopeWhite.contains(Square.of("a7"))).isTrue();
        assertThat(squareScopeWhite.contains(Square.of("b7"))).isTrue();

        assertThat(squareScopeBlack.size()).isEqualTo(3);
        assertThat(squareScopeWhite.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("판의 정보를 가져와서 폰이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movablePawnSquareTest() {
        Map<Square, Piece> board = new HashMap<>();

        board.put(Square.of("b5"), Knight.of(Color.BLACK));
        board.put(Square.of("e5"), Knight.of(Color.BLACK));
        board.put(Square.of("f5"), Knight.of(Color.WHITE));

        Piece piece = Pawn.of(Color.BLACK);
        Set<Square> availableSquares = piece.calculateMovableSquares(Square.of("c6"), board);
        assertThat(availableSquares.contains(Square.of("c5"))).isTrue();
        assertThat(availableSquares.contains(Square.of("b5"))).isFalse();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = piece.calculateMovableSquares(Square.of("e7"), board);
        assertThat(availableSquares.contains(Square.of("e6"))).isTrue();
        assertThat(availableSquares.contains(Square.of("e5"))).isFalse();
        assertThat(availableSquares.size()).isEqualTo(1);

        availableSquares = piece.calculateMovableSquares(Square.of("g6"), board);
        assertThat(availableSquares.contains(Square.of("g5"))).isTrue();
        assertThat(availableSquares.contains(Square.of("f5"))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(2);
    }
}
