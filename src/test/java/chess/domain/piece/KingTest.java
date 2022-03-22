package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹 기물 생성")
    void createKing() {
        Piece king = King.create();
        assertThat(king).isInstanceOf(King.class);
    }
}
