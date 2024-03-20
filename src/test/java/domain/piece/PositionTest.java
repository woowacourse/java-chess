package domain.piece;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("칸의 위치를 생성한다")
    @Test
    void generateSquare() {
        Position position = new Position(new File('b'), new Rank(1));
        Assertions.assertThat(position).isEqualTo(new Position(new File('b'), new Rank(1)));
    }
}
