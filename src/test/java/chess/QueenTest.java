package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    @DisplayName("퀸의 좌표는 (0,3), (7,4)로 생성된다.")
    void createQueen() {
        final List<Queen> queens = Queen.init();

        assertThat(queens).extracting(Queen::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(0, 3),
                        new Position(7, 4)
                );
    }
}
