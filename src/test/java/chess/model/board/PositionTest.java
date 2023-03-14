package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("체스 판의 위치를 관리하는 객체를 생성한다")
    void constructor_givenRankAndFile_thenSuccess() {
        final Position position = assertDoesNotThrow(() -> new Position(File.A, Rank.FIRST));

        assertThat(position).isExactlyInstanceOf(Position.class);
    }
}
