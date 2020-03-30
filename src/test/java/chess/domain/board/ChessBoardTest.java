package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    @Test
    void chessBoardSizeCheck() {
        ChessBoard chessBoard = new ChessBoard();
        Map<BoardSquare, Piece> board = chessBoard.getChessBoard();
        assertThat(board.size()).isEqualTo(32);
    }

    @DisplayName("move 수행이 가능한지 판단하면서 수행, 턴 변경시 수행 불가능한지도 검증")
    @Test
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a6")).isSuccess())
            .isFalse();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a3")).isSuccess())
            .isTrue();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a6")).isSuccess())
            .isTrue();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "b1")).isSuccess())
            .isFalse();
    }

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a3"));
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a3"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(BoardSquare.of("a3")))
            .isEqualTo(Pawn.getPieceInstance(Color.WHITE));
    }

    @Test
    @DisplayName("king 잡혔는지 확인")
    void isKingOnChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("e2", "e4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e1", "e2"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a8", "a6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e2", "e3"));

        assertThat(chessBoard.isKingCaptured()).isFalse();

        chessBoard.movePieceWhenCanMove(new MoveSquare("a6", "d6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e3", "d3"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d6", "d3"));

        assertThat(chessBoard.isKingCaptured()).isTrue();
    }

    @DisplayName("이동하려는 before자리에 말이 없는건지 확인")
    @Test
    void isNoPiece() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.isNoPiece(new MoveSquare("a2", "a3"))).isFalse();
        assertThat(chessBoard.isNoPiece(new MoveSquare("a3", "a4"))).isTrue();
    }

    @DisplayName("이동하려는 before자리의 말이 현재 차례의 말이 아닌지 확인, 말이 없는 경우도 True")
    @Test
    void isNotMyTurn() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.isNotMyTurn(new MoveSquare("a2", "a3"))).isFalse();
        assertThat(chessBoard.isNotMyTurn(new MoveSquare("a3", "a4"))).isTrue();
        assertThat(chessBoard.isNotMyTurn(new MoveSquare("a7", "a6"))).isTrue();
    }

    @DisplayName("폰이 시작지점(즉 상대방의 시작지점)으로 이동했는지 확인")
    @Test
    void mustChangePawnThenCanGo() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b2", "b4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b7", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a4", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c7", "c6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b5", "c6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c8", "b7"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c6", "b7"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("h7", "h6"));
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("b7", "a8")))
            .isEqualTo(MoveState.SUCCESS_BUT_PAWN_CHANGE);
    }

    @DisplayName("폰이 시작지점(즉 상대방의 시작지점)으로 이동했을 때, 변경 여부에 따른 진행 여부")
    @Test
    void mustChangePawnAndCanGo() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b2", "b4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b7", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a4", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c7", "c6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b5", "c6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c8", "b7"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c6", "b7"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("h7", "h6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b7", "a8"));
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("g7", "g6")))
            .isEqualTo(MoveState.FAIL_NOT_ORDER);
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("g2", "g3")))
            .isEqualTo(MoveState.FAIL_MUST_PAWN_CHANGE);
        assertThat(chessBoard.changeFinishPawn(Type.BISHOP)).isEqualTo(MoveState.SUCCESS);
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("g2", "g3")))
            .isEqualTo(MoveState.FAIL_NOT_ORDER);
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("g7", "g6")))
            .isEqualTo(MoveState.SUCCESS);
    }

    @DisplayName("캐슬링 되는지 확인")
    @Test
    void castling() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b2", "b4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b7", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c2", "c4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c7", "c5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b2", "d4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d7", "b5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e2", "e4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e7", "e5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("f2", "f4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("f7", "f5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("g2", "g4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("g7", "g5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("h2", "h4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("h7", "h5"));

        chessBoard.movePieceWhenCanMove(new MoveSquare("b1", "c3"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("b8", "c6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c1", "a3"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("c8", "a6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d1", "e2"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d8", "e7"));
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("e1", "c1")))
            .isEqualTo(MoveState.SUCCESS);
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("e8", "c8")))
            .isEqualTo(MoveState.SUCCESS);
    }
}
