package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ChessBoardInitializerTest {
    @Test
    @DisplayName("체스판을 초기화한다")
    void initChessBoard() {
        // when
        Map<ChessPosition, Piece> chessBoard = ChessBoardInitializer.create();

        // then
        assertThat(chessBoard).hasSize(64);
    }
}
