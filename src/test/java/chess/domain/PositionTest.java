package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("from 메소드에 X좌표와 Y좌표를 전달하여 객체를 생성할 수 있다.")
    @Test
    void from_withAxis() {
        // given
        XAxis aXAxis = XAxis.A;
        YAxis oneYAxis = YAxis.ONE;
        Position position = Position.from(aXAxis, oneYAxis);

        // when & then
        assertThat(position).isNotNull();
    }

    @DisplayName("좌표 객체는 캐싱되어 한번만 생성된다.")
    @Test
    void cache() {
        // given
        Position position = Position.from(XAxis.A, YAxis.ONE);
        Position samePosition = Position.from(XAxis.A, YAxis.ONE);

        // when & then
        assertThat(position).isSameAs(samePosition);
    }
}