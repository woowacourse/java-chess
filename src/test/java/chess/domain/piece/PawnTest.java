package chess.domain.piece;

import chess.domain.Board;
import chess.domain.BoardFixture;
import chess.domain.Direction;
import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.BLACK_PAWN;
import static chess.domain.piece.PieceFixture.WHITE_PAWN;
import static chess.domain.postion.File.A;
import static chess.domain.postion.File.B;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {

    @DisplayName("폰이 두칸 초과로 움직였을 때 에러 테스트")
    @Test
    void overTwoSquare() {
        Pawn pawn = WHITE_PAWN;

        assertThatThrownBy(() -> pawn.canMove(new Position(A, TWO), new Position(A, SIX)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흰색 폰이 위로 한 칸 움직이는지 테스트")
    @Test
    void top_whitePawn() {
        Pawn pawn = WHITE_PAWN;

        assertDoesNotThrow(() -> pawn.canMove(new Position(A, TWO), new Position(A, THREE)));
    }

    @DisplayName("검은색 폰이 아래로 한 칸 움직이는지 테스트")
    @Test
    void bottom_blackPawn() {
        Pawn pawn = BLACK_PAWN;

        assertDoesNotThrow(() -> pawn.canMove(new Position(A, TWO), new Position(A, ONE)));
    }

    @DisplayName("흰색 폰이 아래로 한 칸 움직이면 에러 테스트")
    @Test
    void bottom_whitePawn() {
        Board board = BoardFixture.setup();

        assertThatThrownBy(() -> board.movePiece(new Position(A, TWO), new Position(A, ONE), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검은색 폰이 위로 한 칸 움직이면 에러 테스트")
    @Test
    void top_blackPawn() {
        Board board = BoardFixture.setup();

        assertThatThrownBy(() -> board.movePiece(new Position(A, SEVEN), new Position(A, EIGHT), Team.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("휜색 폰이 처음에 위로 두 칸 이동할 수 있는지 테스트")
    @Test
    void initTopTop() {
        Pawn pawn = WHITE_PAWN;

        assertDoesNotThrow(()
                -> pawn.checkPawn(new Position(A, TWO), new Position(A, FOUR), Direction.TOP, BLACK_PAWN));
    }

    @DisplayName("검은색 폰이 처음에 아래로 두 칸 이동할 수 있는지 테스트")
    @Test
    void initBottomBottom() {
        Pawn pawn = BLACK_PAWN;

        assertDoesNotThrow(()
                -> pawn.checkPawn(new Position(A, SEVEN), new Position(A, FIVE), Direction.TOP, WHITE_PAWN));
    }

    @DisplayName("흰색 폰이 처음이 아닐 때 두 칸 이동하면 에러 테스트")
    @Test
    void notInitTopTop() {
        Pawn pawn = WHITE_PAWN;

        assertThatThrownBy(()
                -> pawn.checkPawn(new Position(A, THREE), new Position(A, FIVE), Direction.TOP, BLACK_PAWN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흰색 폰이 target에 적이 있을 때 대각선으로 한 칸 이동할 수 있는지 테스트")
    @Test
    void diagonal() {
        Pawn pawn = WHITE_PAWN;

        assertDoesNotThrow(()
                -> pawn.checkPawn(new Position(A, TWO), new Position(B, THREE), Direction.TOP, BLACK_PAWN));
    }

    @DisplayName("흰색 폰이 target에 상대 말이 없을 때 대각선으로 이동하면 에러 테스트")
    @Test
    void notDiagonal() {
        Pawn pawn = WHITE_PAWN;

        assertDoesNotThrow(()
                -> pawn.checkPawn(new Position(A, TWO), new Position(B, THREE), Direction.TOP, new Nothing()));
    }
}
