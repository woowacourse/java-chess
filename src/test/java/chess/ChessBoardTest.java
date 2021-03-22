package chess;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initBoard();
    }

    @ParameterizedTest
    @DisplayName("체스 초기 기물 배치 확인")
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        assertThat(chessBoard.getChessBoard().get(i).get(j).getName()).isEqualTo(value);
    }

    @Test
    @DisplayName("폰 움직임 성공")
    void movePawnSuccess() {
        chessBoard.move("b2", "b3");
        assertThat(chessBoard.getChessBoard().get(5).get(1).getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("시작점에 있는 폰은 두 칸 전진 가능")
    void movePawnStart() {
        chessBoard.move("b2", "b4");
        assertThat(chessBoard.getChessBoard().get(4).get(1).getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("폰이 대각선으로 움직여 적 진영 공격 성공")
    void attackPawnSuccess() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        chessBoard.move("b2", "c3");
        assertThat(chessBoard.getChessBoard().get(5).get(2).getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("폰이 적 진영 기물이 있는 직진 위치로는 이동 실패")
    void attackPawnFail() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        assertThatThrownBy(() -> {
            chessBoard.move("c2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 후진할 때 예외처리")
    void movePawnFailLinear() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰이 적 진영 기물이 없는 대각선 위치로 이동 실패")
    void movePawnFailDiagonal() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("제자리 이동 실패")
    void movePawnFailSame() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩 이동 성공")
    void moveRookSuccess() {
        chessBoard.move("a2", "a3");
        chessBoard.move("a1", "a2");
        assertThat(chessBoard.getChessBoard().get(6).get(0).getName()).isEqualTo("r");
    }

    @Test
    @DisplayName("룩 이동 실패")
    void moveRookFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("a1", "a2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩이 이동하여 적 진영 기물 공격 성공")
    void attackRookSuccess() {
        chessBoard.move("a2", "a4");
        chessBoard.move("a1", "a3");
        chessBoard.move("a3", "b3");
        chessBoard.move("b3", "b7");
        assertThat(chessBoard.getChessBoard().get(1).get(1).getName()).isEqualTo("r");
    }

    @Test
    @DisplayName("비숍 이동 성공")
    void moveBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "b2");
        assertThat(chessBoard.getChessBoard().get(6).get(1).getName()).isEqualTo("b");
    }

    @Test
    @DisplayName("비숍 이동 실패")
    void moveBishopFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("c1", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("비숍이 적 진영 공격 성공")
    void attackBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "a3");
        chessBoard.move("a3", "e7");
        assertThat(chessBoard.getChessBoard().get(1).get(4).getName()).isEqualTo("b");
    }

    @Test
    @DisplayName("나이트 이동 성공")
    void moveKnightSuccess() {
        chessBoard.move("b1", "a3");
        assertThat(chessBoard.getChessBoard().get(5).get(0).getName()).isEqualTo("n");
    }

    @Test
    @DisplayName("나이트 이동 실패")
    void moveKnightFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("b1", "d2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 진영 별 점수 계산")
    void scoreTest() {
        chessBoard.move("b2", "b4");
        chessBoard.move("c7", "c5");
        chessBoard.move("b4", "c5");
        assertThat(chessBoard.getScore(Color.WHITE)).isEqualTo(37.0);
        assertThat(chessBoard.getScore(Color.BLACK)).isEqualTo(37.0);
    }

    @Test
    @DisplayName("한 쪽의 킹이 죽고 끝났을 때 점수 계산")
    void scoreTest2() {
        ChessBoard emptyChessBoard = new ChessBoard();
        emptyChessBoard.getSquare(Position.of("b8")).addPiece(new King(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("c8")).addPiece(new Rook(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("a7")).addPiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("c7")).addPiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("d7")).addPiece(new Bishop(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("b6")).addPiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("e6")).addPiece(new Queen(Color.BLACK));

        emptyChessBoard.getSquare(Position.of("f4")).addPiece(new Knight(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("g4")).addPiece(new Queen(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f3")).addPiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("h3")).addPiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f2")).addPiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("g2")).addPiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("e1")).addPiece(new Rook(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f1")).addPiece(new King(Color.WHITE));

        assertThat(emptyChessBoard.getScore(Color.BLACK)).isEqualTo(20.0);
        assertThat(emptyChessBoard.getScore(Color.WHITE)).isEqualTo(19.5);
    }
}
