package chess.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class ChessBoardInitializerTest {
    @Test
    @DisplayName("체스판을 초기화한다")
    void initChessBoard() {
        // given
        ChessBoardInitializer initializer = new ChessBoardInitializer();

        // when
        Map<ChessPosition, Piece> initialBoard = initializer.create();

        // then
        assertThat(initialBoard).hasSize(32);
    }
}
