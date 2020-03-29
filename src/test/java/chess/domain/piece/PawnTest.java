package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BoardSquare;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> Pawn.getPieceInstance(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @Test
    @DisplayName("말의 위치(pawn)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopePawnBlack() {
        Piece pieceBlack = Pawn.getPieceInstance(Color.BLACK);
        Piece pieceWhite = Pawn.getPieceInstance(Color.WHITE);

        Set<BoardSquare> availableSquaresBlack = pieceBlack.getAllCheatSheet(BoardSquare.of("a7"));
        Set<BoardSquare> availableSquaresWhite = pieceWhite.getAllCheatSheet(BoardSquare.of("a6"));

        assertThat(availableSquaresBlack.contains(BoardSquare.of("a6"))).isTrue();
        assertThat(availableSquaresWhite.contains(BoardSquare.of("a7"))).isTrue();

        assertThat(availableSquaresBlack.size()).isEqualTo(1);
        assertThat(availableSquaresWhite.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("폰이 두 칸 움직일 수 있는지 테스트")
    void movablePawnTwoSquareTest() {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("b6"), Knight.getPieceInstance(Color.BLACK));

        Piece pieceBlack = Pawn.getPieceInstance(Color.BLACK);
        Piece pieceWhite = Pawn.getPieceInstance(Color.WHITE);

        Set<BoardSquare> availableSquaresBlack = pieceBlack
            .getCheatSheet(BoardSquare.of("a7"), board);
        Set<BoardSquare> availableSquaresBlack2 = pieceBlack
            .getCheatSheet(BoardSquare.of("b7"), board);
        Set<BoardSquare> availableSquaresWhite = pieceWhite
            .getCheatSheet(BoardSquare.of("a2"), board);

        assertThat(availableSquaresBlack.contains(BoardSquare.of("a5"))).isTrue();
        assertThat(availableSquaresBlack.contains(BoardSquare.of("a6"))).isTrue();
        assertThat(availableSquaresWhite.contains(BoardSquare.of("a3"))).isTrue();
        assertThat(availableSquaresWhite.contains(BoardSquare.of("a4"))).isTrue();
        assertThat(availableSquaresBlack2.contains(BoardSquare.of("b5"))).isFalse();

        assertThat(availableSquaresBlack.size()).isEqualTo(2);
        assertThat(availableSquaresWhite.size()).isEqualTo(2);
        assertThat(availableSquaresBlack2.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("폰 대각선 이동 테스트")
    void movablePawnSquareTest() {
        Map<BoardSquare, Piece> board = new HashMap<>();

        board.put(BoardSquare.of("b5"), Knight.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("e5"), Knight.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("f5"), Knight.getPieceInstance(Color.WHITE));

        Piece blackPawn = Pawn.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = blackPawn.getCheatSheet(BoardSquare.of("c6"), board);
        assertThat(availableBoardSquares.contains(BoardSquare.of("c5"))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(1);

        availableBoardSquares = blackPawn.getCheatSheet(BoardSquare.of("f6"), board);
        assertThat(availableBoardSquares.contains(BoardSquare.of("f5"))).isFalse();
        assertThat(availableBoardSquares.size()).isEqualTo(0);

        availableBoardSquares = blackPawn.getCheatSheet(BoardSquare.of("e6"), board);
        assertThat(availableBoardSquares.contains(BoardSquare.of("f5"))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(1);

        availableBoardSquares = blackPawn.getCheatSheet(BoardSquare.of("g6"), board);
        assertThat(availableBoardSquares.contains(BoardSquare.of("g5"))).isTrue();
        assertThat(availableBoardSquares.contains(BoardSquare.of("f5"))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(2);
    }

}
