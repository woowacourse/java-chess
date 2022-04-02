package chess;

import chess.model.ChessGame;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertThat(chessGame).isExactlyInstanceOf(ChessGame.class);
    }

    @Test
    @DisplayName("왕이 죽었는지를 확인하는 테스트")
    void isKingDead() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);
        assertThat(chessGame.isKingDead()).isFalse();
    }
}
