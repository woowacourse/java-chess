package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("킹의 좌표는 (0,4), (7,3)로 생성된다.")
    void createKing() {
        final List<King> kings = King.createKings();

        assertThat(kings).extracting(King::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(0, 4),
                        new Position(7, 3)
                );
    }
}
