package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.board.Board;
import domain.piece.Pawn;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ChessGameTest {

    private static final Location WHITE_PAWN_START = Location.of(1, 1);
    private static final Location WHITE_PAWN_END = Location.of(1, 2);
    private static final Location BLACK_PAWN_START = Location.of(1, 6);
    private static final Location BLACK_PAWN_END = Location.of(1, 5);

    @DisplayName("순서에 따라서 올바른 진영의 체스말을 움직인다.")
    @TestFactory
    public Stream<DynamicTest> testMoveSuccess() {
        final ChessGame chessGame = new ChessGame();
        chessGame.initialize();
        return Stream.of(
            DynamicTest.dynamicTest("첫 순서에 흰색 진영의 폰을 한칸 전진한다.", () -> {
                    assertDoesNotThrow(
                        () -> chessGame.move(WHITE_PAWN_START, WHITE_PAWN_END)
                    );
                    final Board board = chessGame.getBoard();
                    assertThat(board.findSquare(WHITE_PAWN_START).isNotEmpty()).isFalse();
                    assertThat(board.findSquare(WHITE_PAWN_END).getPiece()).isEqualTo(Pawn.makeWhite());
                }
            ),
            DynamicTest.dynamicTest("두번째 순서에 검은색 진영의 폰을 한칸 전진한다.", () -> {
                    assertDoesNotThrow(
                        () -> chessGame.move(BLACK_PAWN_START, BLACK_PAWN_END)
                    );
                    final Board board = chessGame.getBoard();
                    assertThat(board.findSquare(BLACK_PAWN_START).isNotEmpty()).isFalse();
                    assertThat(board.findSquare(BLACK_PAWN_END).getPiece()).isEqualTo(Pawn.makeBlack());
                }
            )
        );
    }

    @DisplayName("순서가 아닌 진영의 체스말을 움직인다.")
    @Nested
    class TestMoveFail {

        @DisplayName("흰색 진영의 순서일 때 검은 진영의 체스말을 움직이면 오류를 던진다.")
        @Test
        public void testMoveFailBlack() {
            final ChessGame chessGame = new ChessGame();
            chessGame.initialize();

            assertThatThrownBy(
                () -> chessGame.move(BLACK_PAWN_START, BLACK_PAWN_END)
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("검은색 진영의 순서일 때 흰색 진영의 체스말을 움직이면 오류를 던진다.")
        @Test
        public void testMoveFailWhite() {
            final ChessGame chessGame = new ChessGame();
            chessGame.initialize();
            chessGame.move(WHITE_PAWN_START, WHITE_PAWN_END);

            assertThatThrownBy(
                () -> chessGame.move(Location.of(2, 1), Location.of(2, 2))
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
