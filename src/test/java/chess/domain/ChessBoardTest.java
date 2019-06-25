package chess.domain;

import chess.domain.chesspiece.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class ChessBoardTest {
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