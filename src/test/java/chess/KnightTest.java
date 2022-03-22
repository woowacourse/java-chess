package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    @DisplayName("나이트의 좌표는 (0,1), (0,6), (7,6), (7,1)로 생성된다.")
    void createKnight() {
        final List<Knight> knights = Knight.createKnights();

        assertThat(knights).extracting(Knight::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(0, 1),
                        new Position(0, 6),
                        new Position(7, 6),
                        new Position(7, 1)
                );
    }
}
