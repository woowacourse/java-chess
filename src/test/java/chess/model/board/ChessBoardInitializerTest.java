package chess.model.board;

import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class ChessBoardInitializerTest {
    @Test
    @DisplayName("체스판을 초기화한다")
    void initChessBoard() {
        // when
        Map<ChessPosition, Piece> chessBoard = ChessBoardInitializer.INITIAL_BOARD;

        // then
        assertThat(chessBoard).hasSize(64);
    }
}
