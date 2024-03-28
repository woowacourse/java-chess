package domain.board;

import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_SEVEN;
import static domain.PositionFixture.A_THREE;
import static domain.PositionFixture.B_EIGHT;
import static domain.PositionFixture.B_FOUR;
import static domain.PositionFixture.B_ONE;
import static domain.PositionFixture.B_SIX;
import static domain.PositionFixture.B_THREE;
import static domain.PositionFixture.B_TWO;
import static domain.PositionFixture.C_EIGHT;
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
import static domain.PositionFixture.E_ONE;
import static domain.PositionFixture.E_SIX;
import static domain.PositionFixture.E_TWO;
import static domain.PositionFixture.F_FOUR;
import static domain.PositionFixture.F_ONE;
import static domain.PositionFixture.F_SIX;
import static domain.PositionFixture.F_THREE;
import static domain.PositionFixture.F_TWO;
import static domain.PositionFixture.G_EIGHT;
import static domain.PositionFixture.G_FOUR;
import static domain.PositionFixture.G_TWO;
import static domain.PositionFixture.H_THREE;
import static domain.PositionFixture.H_TWO;
import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.position.Position;
import domain.piece.Bishop;
import domain.piece.Empty;
import domain.piece.InitPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.MovedPawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("현재 차례가 아니라면 예외가 발생한다")
    void turn() {
        final Board board = new Board(BoardInitiator.init());
        board.move(B_TWO, B_THREE);

        assertThatThrownBy(() -> board.move(B_THREE, B_FOUR)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례: BLACK, 현재 차례의 말만 움직일 수 있습니다");
    }

    @Test
    @DisplayName("이동 경로에 다른 말이 있다면 예외가 발생한다")
    void path() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(A_ONE, A_THREE)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 놓져 있습니다");
    }

    @Test
    @DisplayName("해당 말로 이동할 수 없는 위치가 입력되면 예외가 발생한다")
    void reachability() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(B_TWO, H_TWO)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말로 해당 위치를 갈 수 없습니다");

    }

    @Test
    @DisplayName("체스판에서 빈칸을 이동시키려고 하면 예외가 발생한다")
    void emptySource() {
        final Board board = new Board(BoardInitiator.init());

        assertThatThrownBy(() -> board.move(C_THREE, C_FOUR)).isInstanceOf(IllegalArgumentException.class)
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
        }).doesNotThrowAnyException();

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
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰이 세로로 있지 않을 경우 점수를 계산할 수 있다")
    void pawn() {
        final Board board = new Board(BoardInitiator.init());
        assertThat(board.calculateScore(WHITE)).isEqualTo(38);
    }

    /*
                ........
                ........
                ........
                ........
                .p......
                .p......
                .p.....
                ........
     */

    @Test
    @DisplayName("폰이 일직선상 있을 때 점수를 계산한다")
    void pieceOnSameFile() {
        final Map<Position, Piece> positionPieceMap = BoardInitiator.create(
                entry(B_TWO, new InitPawn(WHITE)),
                entry(B_THREE, new InitPawn(WHITE)),
                entry(B_FOUR, new InitPawn(WHITE))
        );
        final Board board = new Board(positionPieceMap);
        assertThat(board.calculateScore(WHITE)).isEqualTo(1.5);
    }

    /*
        .KR.....  8
        P.PB....  7
        .P..Q...  6
        ........  5
        .....nq.  4
        .....p.p  3
        .....pp.  2
        ....rk..  1

        abcdefgh
     */

    @Test
    @DisplayName("보드판에 남아있는 피스의 점수를 계산한다")
    void calculateScore() {
        final Map<Position, Piece> positionPieceMap = BoardInitiator.create(
                entry(B_EIGHT, new King(BLACK)),
                entry(C_EIGHT, new Rook(BLACK)),
                entry(A_SEVEN, new MovedPawn(BLACK)),
                entry(C_SEVEN, new MovedPawn(BLACK)),
                entry(D_SEVEN, new Bishop(BLACK)),
                entry(B_SIX, new MovedPawn(BLACK)),
                entry(E_SIX, new Queen(BLACK)),
                entry(F_FOUR, new Knight(WHITE)),
                entry(G_FOUR, new Queen(WHITE)),
                entry(F_THREE, new MovedPawn(WHITE)),
                entry(H_THREE, new MovedPawn(WHITE)),
                entry(F_TWO, new MovedPawn(WHITE)),
                entry(G_TWO, new MovedPawn(WHITE)),
                entry(E_ONE, new Rook(WHITE)),
                entry(F_ONE, new King(WHITE))
        );

        final Board board = new Board(positionPieceMap);
        assertThat(board.calculateScore(BLACK)).isEqualTo(20);
        assertThat(board.calculateScore(WHITE)).isEqualTo(19.5);
    }

}
