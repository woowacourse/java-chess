package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EmptyTest {

    @Test
    void is_empty() {
        Piece piece = new Empty(Symbol.EMPTY, Team.NONE);
        assertThat(piece.isEmpty()).isTrue();
    }
}
