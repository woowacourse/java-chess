package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceStorageTest {

    @Test
    @DisplayName("킹을 조회한다.")
    void findKing() {
        final Piece piece = PieceStorage.valueOf(Name.KING.name(), Color.BLACK.name());

        assertThat(piece).isInstanceOf(King.class);
    }
}
