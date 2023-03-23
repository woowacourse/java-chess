package chess.domain;

import static chess.domain.square.File.A;
import static chess.domain.square.File.B;
import static chess.domain.square.Rank.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {
    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("보드가 생성되면 32개의 Piece를 가진다.")
    void containsPieces() {
        Board board = new Board();

        assertThat(board.getBoard()).hasSize(32);
    }

    @Test
    @DisplayName("말은 규칙에 따라 움직인다.")
    void move() {
        Board board = new Board();
        Square src = Square.of(A, TWO);
        Square dst = Square.of(A, THREE);

        board.move(src, dst);

        assertThat(board.getBoard().keySet()).contains(dst);
    }

    @Test
    @DisplayName("knight는 가는 길목에 말이 있어도 움직일 수 있다.")
    void knightCanMove() {
        Board board = new Board();
        Square src = Square.of(B, ONE);
        Square dst = Square.of(A, THREE);

        board.move(src, dst);

        assertThat(board.getBoard().keySet()).contains(dst);
    }

    @Test
    @DisplayName("knight는 이동할 칸에 말이 있으면 이동할 수 없다.")
    void knightCannotMove() {
        Board board = new Board();
        Square src = Square.of(A, TWO);
        Square dst = Square.of(A, THREE);
        board.move(src, dst);

        Square src1 = Square.of(B, ONE);
        Square dst1 = Square.of(A, THREE);

        assertThrows(IllegalArgumentException.class, () -> board.move(src1, dst1));
    }

    @Nested
    class pieceMove {

        Board board = new Board();

        @BeforeEach
        void boardInit() {
            board = new Board();

            Square src = Square.of(A, TWO);
            Square dst = Square.of(A, FOUR);

            board.move(src, dst);

            src = Square.of(B, TWO);
            dst = Square.of(B, FOUR);
            board.move(src, dst);
        }

        @ParameterizedTest(name = "knight를 제외한 다른 말들은 가는 길목에 말이 없어야 이동할 수 있다.")
        @CsvSource({"A,ONE,A,THREE", "C,ONE,B,TWO"})
        void pieceCanMove(File srcFile, Rank srcRank, File dstFile, Rank dstRank) {
            Square src = Square.of(srcFile, srcRank);
            Square dst = Square.of(dstFile, dstRank);

            board.move(src, dst);

            assertThat(board.getBoard().keySet()).contains(dst);
        }

        @ParameterizedTest(name = "knight를 제외한 다른 말들은 가는 길목에 말이 있으면 이동할 수 없다.")
        @CsvSource({"A,ONE,A,FIVE", "D,ONE,B,THREE"})
        void pieceCannotMove(File srcFile, Rank srcRank, File dstFile, Rank dstRank) {
            Square src = Square.of(srcFile, srcRank);
            Square dst = Square.of(dstFile, dstRank);

            assertThatThrownBy(() -> board.move(src, dst))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동 경로에 말이 존재합니다.");
        }
    }
}
