package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("생성자를 통해 생성될 수 있다.")
    @Test
    void constructor() {
        // given
        Bishop bishop = new Bishop();

        // when & then
        assertThat(bishop).isNotNull();
    }
}