package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("자신의 종류를 반환한다.")
    @Test
    void findKind() {
        assertThat(new Bishop(Position.of(1, 1), Team.BLACK).findKind())
                .isEqualTo(Kind.BISHOP);
    }
}