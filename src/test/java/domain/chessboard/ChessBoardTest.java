package domain.chessboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final int sum = ChessBoardFactory.generate()
                .getChessBoard()
                .values().size();

        assertThat(sum).isEqualTo(32);
    }

}
