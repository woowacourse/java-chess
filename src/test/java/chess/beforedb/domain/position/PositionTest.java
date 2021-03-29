package chess.beforedb.domain.position;

import static chess.beforedb.domain.position.type.File.A;
import static chess.beforedb.domain.position.type.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import chess.db.domain.position.PositionEntity;
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
            assertThat(PositionEntity.of(file, rank)).isEqualTo(new PositionEntity(file, rank));
        }
    }

    @DisplayName("유효한 위치")
    @Test
    void validPosition() {
        PositionEntity position = PositionEntity.of("a3");

        assertThat(position).isEqualTo(new PositionEntity(A, THREE));
    }

    @DisplayName("유효하지 않은 위치")
    @Test
    void invalidPosition() {
        assertThatThrownBy(() -> PositionEntity.of("a0"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}