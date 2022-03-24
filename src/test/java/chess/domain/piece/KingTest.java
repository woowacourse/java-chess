package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("킹이다.")
    void isKing() {
        assertThat(new King(Color.WHITE).isKing()).isTrue();
    }
}