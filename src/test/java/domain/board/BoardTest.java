package domain.board;

import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_THREE;
import static domain.PositionFixture.B_EIGHT;
import static domain.PositionFixture.B_FOUR;
import static domain.PositionFixture.B_ONE;
import static domain.PositionFixture.B_THREE;
import static domain.PositionFixture.B_TWO;
import static domain.PositionFixture.C_FOUR;
import static domain.PositionFixture.C_SEVEN;
import static domain.PositionFixture.C_SIX;
import static domain.PositionFixture.C_THREE;
import static domain.PositionFixture.D_FIVE;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_ONE;
import static domain.PositionFixture.D_SEVEN;
import static domain.PositionFixture.D_SIX;
import static domain.PositionFixture.D_TWO;
import static domain.PositionFixture.E_FOUR;
import static domain.PositionFixture.E_TWO;
import static domain.PositionFixture.F_SIX;
import static domain.PositionFixture.G_EIGHT;
import static domain.PositionFixture.H_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Knight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("현재 차례가 아니라면 예외가 발생한다")
    void turn() {
        final Board board = new Board(BoardInitiator.init());
        board.move(B_TWO, B_THREE);

        assertThatThrownBy(() -> board.move(B_THREE, B_FOUR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례: BLACK, 현재 차례의 말만 움직일 수 있습니다");
    }

    @Test
    @DisplayName("이동 경로에 다른 말이 있다면 예외가 발생한다")
    void path() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(A_ONE, A_THREE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 놓져 있습니다");
    }

    @Test
    @DisplayName("해당 말로 이동할 수 없는 위치가 입력되면 예외가 발생한다")
    void reachability() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(B_TWO, H_TWO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말로 해당 위치를 갈 수 없습니다");

    }

    @Test
    @DisplayName("체스판에서 빈칸을 이동시키려고 하면 예외가 발생한다")
    void emptySource() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(C_THREE, C_FOUR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 말이 선택되지 않았습니다");

    }


    @Test
    @DisplayName("보드에서 말이 움직인 자리가 빈 칸이 되는지 확인한다")
    void empty() {
        final Board board = new Board(BoardInitiator.init());

        board.move(B_ONE, A_THREE);

        assertThat(board.getSquares().get(B_ONE)).isInstanceOf(Empty.class);
        assertThat(board.getSquares().get(A_THREE)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("보드에서 말이 잘 움직여지는지 확인한다")
    void playChess() {
        final Board board = new Board(BoardInitiator.init());

        Assertions.assertThatCode(() -> {
                    board.move(E_TWO, E_FOUR);
                    board.move(C_SEVEN, C_SIX);
                    board.move(D_TWO, D_FOUR);
                    board.move(D_SEVEN, D_FIVE);
                    board.move(B_ONE, C_THREE);
                    board.move(D_FIVE, E_FOUR);
                    board.move(C_THREE, E_FOUR);
                    board.move(B_EIGHT, D_SEVEN);
                    board.move(D_ONE, E_TWO);
                    board.move(G_EIGHT, F_SIX);
                    board.move(E_FOUR, D_SIX);
                }
        ).doesNotThrowAnyException();

    }

    @Test
    @DisplayName("문자열로 시작과 끝 위치가 주어져도 보드에서 말이 잘 움직여진다.")
    void playChess2() {
        final Board board = new Board(BoardInitiator.init());

        Assertions.assertThatCode(() -> {
                    board.move("e2", "e4");
                    board.move("c7", "c6");
                    board.move("d2", "d4");
                    board.move("d7", "d5");
                    board.move("b1", "c3");
                    board.move("d5", "e4");
                    board.move("c3", "e4");
                    board.move("b8", "d7");
                    board.move("d1", "e2");
                    board.move("g8", "f6");
                    board.move("e4", "d6");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰이 세로로 있지 않을 경우 점수를 계산할 수 있다")
    void pawn() {
        final Board board = new Board(BoardInitiator.init());
        assertThat(board.calculateScore(Color.WHITE)).isEqualTo(38);
    }

    @Test
    @DisplayName("폰이 세로로 있는 경우 점수를 계산할 수 있다")
    void pawnOnSameFile() {
        final Board board = new Board(BoardInitiator.init());

        board.move("b2", "b4");
        board.move("a7", "a5");
        board.move("b4", "a5");
        assertThat(board.calculateScore(Color.WHITE)).isEqualTo(34);
    }
}
