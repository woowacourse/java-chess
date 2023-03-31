package chess.model.board;

import static chess.model.board.PositionFixture.A1;
import static chess.model.board.PositionFixture.B2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.position.Distance;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("생성자는 체스 판의 위치를 관리하는 객체를 생성한다.")
    void constructor_givenRankAndFile_thenSuccess() {
        final Position position = assertDoesNotThrow(() -> Position.of(File.A, Rank.FIRST));

        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    @DisplayName("differ()는 다른 Position을 건네주면 각 Position의 거리를 반환한다.")
    void differ_givenOtherPosition_thenReturnDistance() {
        // when
        final Distance result = A1.differ(B2);

        // then
        assertThat(result.rank()).isEqualTo(-1);
        assertThat(result.file()).isEqualTo(-1);
    }
}
