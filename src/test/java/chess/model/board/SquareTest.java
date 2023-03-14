package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("체스판 칸이 정상적으로 생성되는지 확인한다.")
    void constructor_givenRankAndFile_thenSuccess() {
        final Square square = assertDoesNotThrow(() -> new Square(Rank.FIRST, File.A));

        assertThat(square).isExactlyInstanceOf(Square.class);
    }
}
