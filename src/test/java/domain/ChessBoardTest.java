package domain;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스게임을 시작하면 8*8 체스판을 생성한다.")
    void when_start_chess_game_create_chess_board() {
        ChessBoard chessBoard = new ChessBoard();
        Piece[][] testChessBoard = chessBoard.getChessBoard();

        assertThat(testChessBoard).hasSize(8);

        for (Piece[] row : testChessBoard) {
            assertThat(row).hasSize(8);
        }
    }
}
