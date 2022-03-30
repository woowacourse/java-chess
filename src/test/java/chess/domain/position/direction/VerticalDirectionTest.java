package chess.domain.position.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerticalDirectionTest {

    @DisplayName("isInVerticalRange는 현재 위치와 다른 위치가 주어진 세로 거리 안에 있는지 체크한다.")
    @Test
    void isInVerticalRange_returnsTrue() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.TWO);
        Position position2 = Position.from(XAxis.C, YAxis.ONE);

        // when
        boolean actual = VerticalDirection.isInVerticalRange(position1, position2, 2);

        // then
        assertThat(actual).isTrue();
    }
}
