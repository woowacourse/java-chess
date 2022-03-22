package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    @DisplayName("룩의 좌표는 (0,0), (0,7), (7,0), (7,7)로 생성된다.")
    void createRook() {
        final List<Rook> rooks = Rook.createRooks();

        assertThat(rooks).extracting(Rook::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(0, 0),
                        new Position(0, 7),
                        new Position(7, 7),
                        new Position(7, 0)
                );
    }
}
