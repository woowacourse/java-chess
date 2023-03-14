package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Bishop bishop = new Bishop();

        List<Position> movablePositions = bishop.findMovablePositions(new Position(2, 2));

        assertThat(movablePositions).contains(new Position(1, 1), new Position(1, 3), new Position(3, 1),
                new Position(3, 3),
                new Position(8, 8));

        assertThat(movablePositions.size()).isEqualTo(9);
    }

}
