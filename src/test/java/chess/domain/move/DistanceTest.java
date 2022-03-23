package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistanceTest {

    @Test
    @DisplayName("두 포지션을 받아 포지션 간 거리를 계산한다.")
    void of() {
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a6");

        Distance distance = Distance.of(source, target);
        assertAll(
                () -> assertThat(distance.getHorizon()).isEqualTo(0),
                () -> assertThat(distance.getVertical()).isEqualTo(-1)
        );


    }
}
