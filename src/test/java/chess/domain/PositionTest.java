package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("Position을 생성할 때 캐싱이 되는 지 확인")
    void createPosition() {
        Position position = Position.of(1, 2);
        Assertions.assertThat(position).isEqualTo(Position.of(1, 2));
        Assertions.assertThat(position).isSameAs(Position.of(1, 2));
    }

    @Test
    @DisplayName("Direction을 받을 때 앞으로 나아가는 지 확인")
    void goPosition() {
        Position position = Position.of(1, 2);
        Assertions.assertThat(position.go(PieceDirection.RIGHT)).isSameAs(Position.of(2, 2));
    }

    @Test
    @DisplayName("Direction을 받을 때 가지 못하는 지 확인")
    void invalidGo() {
        Position position = Position.of(0, 0);
        Assertions.assertThat(position.invalidGo(PieceDirection.UP)).isFalse();
        Assertions.assertThat(position.invalidGo(PieceDirection.DOWN)).isTrue();
        Assertions.assertThat(position.invalidGo(PieceDirection.RIGHT)).isFalse();
        Assertions.assertThat(position.invalidGo(PieceDirection.LEFT)).isTrue();
    }
}