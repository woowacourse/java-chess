package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class PositionTest {
    @Test
    void x축_포지션_초기화() {
        assertThatCode(() -> new Position(8)).doesNotThrowAnyException();
    }

    @Test
    void x축_포지션_비정상_초기화() {
        assertThrows(IllegalArgumentException.class, () -> new Position(9));
    }

    @Test
    void 좌로_이동() {
        Position position = new Position(3);
        assertThat(position.moveBack(1)).isEqualTo(new Position(2));
    }

    @Test
    void 좌로_이동_2칸() {
        Position position = new Position(3);
        assertThat(position.moveBack(2)).isEqualTo(new Position(1));
    }

    @Test
    void 좌로_이동_경계값() {
        Position xPosition = new Position(1);
        assertThat(xPosition.moveBack(1)).isEqualTo(new Position(1));
    }

    @Test
    void 우로_이동() {
        Position xPosition = new Position(1);
        Position position = xPosition.moveGo(1);
        assertThat(position).isEqualTo(new Position(2));
    }

    @Test
    void 우로_이동_경계값() {
        Position position = new Position(8);
        assertThat(position.moveGo(1)).isEqualTo(new Position(8));
    }
}
