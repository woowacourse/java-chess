package chess.domain.position.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiagonalDirectionTest {

    @DisplayName("getPositionsSameDirectionDiagonalBetween 은 대각방향의 두 위치 사이의 위치 리스트를 반환한다.")
    @Test
    void getPositionsSameDirectionDiagonalBetween() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.D, YAxis.FOUR);

        // when
        List<Position> positions = DiagonalDirection.getPositionsSameDirectionDiagonalBetween(position1, position2);

        // then
        assertThat(positions).containsAll(List.of(
                Position.from(XAxis.B, YAxis.TWO),
                Position.from(XAxis.C, YAxis.THREE)
        ));
    }
}
