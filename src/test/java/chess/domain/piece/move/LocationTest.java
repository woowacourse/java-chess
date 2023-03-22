package chess.domain.piece.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationTest {

    @Test
    @DisplayName("인자로 위치 정보를 넣어주면 해당 위치 정보가 추가된다.")
    void add() {
        // given
        Location location = new Location();

        // when
        location.add(Set.of(new Position(0, 0), new Position(0, 1)));

        // then
        assertThat(location.contains(new Position(0, 0)))
                .isTrue();
    }

    @Test
    @DisplayName("인자로 위치 정보를 넣어주면 해당 위치 정보가 포함되어 있는지에 대한 여부를 반환한다.")
    void contains() {
        // given
        Location location = new Location();
        location.add(Set.of(new Position(0, 0)));

        // when
        boolean isContains = location.contains(new Position(0, 1));

        // then
        assertThat(isContains)
                .isFalse();
    }
}
