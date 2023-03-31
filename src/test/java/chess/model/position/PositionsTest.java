package chess.model.position;

import static chess.model.board.PositionFixture.A1;
import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A4;
import static chess.model.board.PositionFixture.A5;
import static chess.model.board.PositionFixture.A6;
import static chess.model.board.PositionFixture.A7;
import static chess.model.board.PositionFixture.A8;
import static chess.model.board.PositionFixture.B1;
import static chess.model.board.PositionFixture.C1;
import static chess.model.board.PositionFixture.D1;
import static chess.model.board.PositionFixture.E1;
import static chess.model.board.PositionFixture.F1;
import static chess.model.board.PositionFixture.G1;
import static chess.model.board.PositionFixture.H1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsTest {

    @Test
    @DisplayName("같은 file들의 Position을 반환한다.")
    void getPositionBy_givenFile_thenReturnSameFilePositions() {
        // when
        final List<Position> sameFilePositions = Positions.getPositionsBy(File.A);

        // then
        assertAll(
                () -> assertThat(sameFilePositions).hasSize(8),
                () -> assertThat(sameFilePositions).containsExactly(A1, A2, A3, A4, A5, A6, A7, A8)
        );
    }

    @Test
    @DisplayName("같은 rank들의 Position을 반환한다.")
    void getPositionBy_givenRank_thenReturnSameFilePositions() {
        // when
        final List<Position> sameRankPositions = Positions.getPositionsBy(Rank.FIRST);

        // then
        assertAll(
                () -> assertThat(sameRankPositions).hasSize(8),
                () -> assertThat(sameRankPositions).containsExactly(A1, B1, C1, D1, E1, F1, G1, H1)
        );
    }
}
