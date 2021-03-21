package chess.domain.position;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.File;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @DisplayName("위치 캐싱")
    @Test
    void positionsCaching() {
        for (File file : File.values()) {
            assertPositionCachedByFile(file);
        }
    }

    private void assertPositionCachedByFile(File file) {
        for (Rank rank : Rank.values()) {
            assertThat(Position.of(file, rank)).isEqualTo(new Position(file, rank));
        }
    }

    @DisplayName("유효한 위치")
    @Test
    void validPosition() {
        Position position = Position.of("a3");

        assertThat(position).isEqualTo(new Position(A, THREE));
    }

    @DisplayName("유효하지 않은 위치")
    @Test
    void invalidPosition() {
        assertThatThrownBy(() -> Position.of("a0"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}