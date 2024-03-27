package domain.position;

import domain.game.Vector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("위치를 생성한다")
    @Test
    void generatePosition() {
        Position position = new Position(new File('b'), new Rank(1));
        Assertions.assertThat(position).isEqualTo(new Position(new File('b'), new Rank(1)));
    }

    @DisplayName("차이를 계산한다.")
    @Test
    void subtractPosition() {
        Position source = new Position(new File('b'), new Rank(1));
        Position target = new Position(new File('c'), new Rank(2));

        Assertions.assertThat(source.generateVectorToTargetPosition(target)).isEqualTo(new Vector(-1, -1));
    }

}
