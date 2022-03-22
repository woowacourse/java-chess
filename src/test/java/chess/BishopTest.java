package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    @DisplayName("비숍의 좌표는 (0,2), (0,5), (7,2), (7,5)로 생성된다.")
    void createBishop() {
        final List<Bishop> bishops = Bishop.init();

        assertThat(bishops).extracting(Bishop::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(0, 2),
                        new Position(0, 5),
                        new Position(7, 2),
                        new Position(7, 5)
                );
    }
}
