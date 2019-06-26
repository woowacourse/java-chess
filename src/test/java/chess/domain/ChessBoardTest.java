package chess.domain;

import chess.domain.chesspiece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessBoardTest {
    @Test
    void 경로가_NONE() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.canMove(Position.of(0, 1), Position.of(2, 2))).isTrue();
    }

    @Test
    void 경로에_말이_있을_때() {
        ChessBoard chessBoard = new ChessBoard();
        System.out.println(chessBoard.canMove(Position.of(0, 7), Position.of(5, 7)));
        assertThat(chessBoard.canMove(Position.of(0, 7), Position.of(5, 7))).isFalse();
    }

    @Test
    void 말_위치_이동() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 3), Position.of(3, 3));
        chessBoard.movePiece(Position.of(0, 2), Position.of(5, 7));

        assertThat(chessBoard.getChessBoard().get(Position.of(3, 3)).getClass() == Pawn.class).isTrue();
        assertThat(chessBoard.getChessBoard().get(Position.of(5, 7)).getClass() == Bishop.class).isTrue();
    }

    @Test
    void 폰_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 2), Position.of(3, 2));
        chessBoard.movePiece(Position.of(6, 3), Position.of(4, 3));


        chessBoard.movePiece(Position.of(3, 2), Position.of(4, 3));


        assertThat(chessBoard.getChessBoard().get(Position.of(4, 3)).getClass() == Pawn.class).isTrue();
        assertThat(chessBoard.getChessBoard().get(Position.of(3, 2)).getClass() == Blank.class).isTrue();
    }

    @Test
    void 폰_적이_있는_경로에서_직선_이동() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(6, 4), Position.of(5, 4));
        chessBoard.movePiece(Position.of(7, 5), Position.of(2, 0));


        chessBoard.movePiece(Position.of(1, 0), Position.of(2, 0));


        assertThat(chessBoard.getChessBoard().get(Position.of(1, 0)).getClass() == Pawn.class).isTrue();
        assertThat(chessBoard.getChessBoard().get(Position.of(2, 0)).getClass() == Bishop.class).isTrue();
    }

    @Test
    void 비숍_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 3), Position.of(2, 3));
        System.out.println(chessBoard.getChessBoard().get(Position.of(2, 3)));
        chessBoard.movePiece(Position.of(0, 2), Position.of(2, 4));
        System.out.println(chessBoard.getChessBoard().get(Position.of(2, 4)));
        chessBoard.movePiece(Position.of(2, 4), Position.of(6, 0));
        System.out.println(chessBoard.getChessBoard().get(Position.of(6, 0)));


        assertThat(chessBoard.getChessBoard().get(Position.of(6, 0)).getClass() == Bishop.class).isTrue();
    }

    @Test
    void 킹_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 3), Position.of(2, 3));
        chessBoard.movePiece(Position.of(6, 4), Position.of(5, 4));
        chessBoard.movePiece(Position.of(7, 5), Position.of(3, 1));
        chessBoard.movePiece(Position.of(3, 1), Position.of(1, 3));
        chessBoard.movePiece(Position.of(0, 3), Position.of(1, 3));


        assertThat(chessBoard.getChessBoard().get(Position.of(1, 3)).getClass() == King.class).isTrue();
    }


    @Test
    void 퀸_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 3), Position.of(2, 3));
        chessBoard.movePiece(Position.of(0, 4), Position.of(3, 1));
        chessBoard.movePiece(Position.of(3, 1), Position.of(6, 1));


        assertThat(chessBoard.getChessBoard().get(Position.of(6, 1)).getClass() == Queen.class).isTrue();
    }


    @Test
    void 나이트_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(0, 1), Position.of(2, 2));
        chessBoard.movePiece(Position.of(2, 2), Position.of(4, 3));
        chessBoard.movePiece(Position.of(4, 3), Position.of(6, 4));


        assertThat(chessBoard.getChessBoard().get(Position.of(6, 4)).getClass() == Knight.class).isTrue();
    }

    @Test
    void 룩_공격() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 1), Position.of(3, 1));
        chessBoard.movePiece(Position.of(6, 0), Position.of(4, 0));
        chessBoard.movePiece(Position.of(4, 0), Position.of(3, 1));
        chessBoard.movePiece(Position.of(7, 0), Position.of(1, 0));


        assertThat(chessBoard.getChessBoard().get(Position.of(1, 0)).getClass() == Rook.class).isTrue();
    }

    @Test
    void 게임종료() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(1, 4), Position.of(2, 4));
        chessBoard.movePiece(Position.of(6, 3), Position.of(5, 3));
        chessBoard.movePiece(Position.of(7, 2), Position.of(3, 6));
        assertThat(chessBoard.isGameOver()).isFalse();
        chessBoard.movePiece(Position.of(3, 6), Position.of(0, 3));

        assertThat(chessBoard.isGameOver()).isTrue();
    }

    @Test
    void 폰이_같은_세로줄에_없을_때_점수계산() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(6, 3), Position.of(5, 3));
        chessBoard.movePiece(Position.of(7, 2), Position.of(3, 6));
        chessBoard.movePiece(Position.of(3, 6), Position.of(1, 4));
        chessBoard.movePiece(Position.of(1, 4), Position.of(0, 3));

        assertThat(chessBoard.calculateScore(Team.BLACK)).isEqualTo(37.0, offset(0.00099));
        assertThat(chessBoard.calculateScore(Team.WHITE)).isEqualTo(38.0, offset(0.00099));
    }

    @Test
    void 폰이_같은_세로줄에_있을_때_점수계산() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of(6, 3), Position.of(4, 3));
        chessBoard.movePiece(Position.of(1, 4), Position.of(3, 4));
        chessBoard.movePiece(Position.of(4, 3), Position.of(3, 4));
        chessBoard.movePiece(Position.of(7, 2), Position.of(3, 6));
        chessBoard.movePiece(Position.of(3, 6), Position.of(0, 3));

        assertThat(chessBoard.calculateScore(Team.BLACK)).isEqualTo(37.0, offset(0.00099));
        assertThat(chessBoard.calculateScore(Team.WHITE)).isEqualTo(37.0, offset(0.00099));
    }
}