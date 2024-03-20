package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ChessBoardInitializerTest {
    @Test
    @DisplayName("체스판을 초기화한다")
    void initChessBoard() {
        // given
        ChessBoardInitializer initializer = new ChessBoardInitializer();

        // when
        List<Piece> pieces = initializer.create();

        // then
        assertThat(pieces).hasSize(32);
    }
}
