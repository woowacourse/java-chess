package chess.domain;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    @Test
    @DisplayName("포지션 입력 값이 범위를 벗어나면 예외가 발생한다.")
    void validateLength() {
        assertThatThrownBy(() -> Position.from("J3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("출발 Position으로부터 도착 Position까지의 경로를 저장하는 Position 리스트 조회")
    void validatePositionList() {
        Position position = Position.from("a1");
        List<Position> positions = position.getPathToDst(Position.from("f6"), Direction.NORTHEAST);
        assertThat(positions)
                .containsExactly(Position.from("b2"), Position.from("c3"), Position.from("d4"),Position.from("e5"));
    }
}
