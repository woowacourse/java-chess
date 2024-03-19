package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("(1, 1) -> (2, 1)")
    void findMovablePositions() {
        King king = new King(new Position(1, 1), Color.WHITE);
        Position destination = new Position(2, 1);

        assertThat(king.findMovablePositions(destination)).contains(destination);
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        King king = new King(new Position(1, 1), Color.WHITE);

        Assertions.assertAll(
                () -> assertThat(king.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(king.isSameColor(Color.BLACK)).isFalse()
        );
    }
}