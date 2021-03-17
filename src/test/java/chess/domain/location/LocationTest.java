package chess.domain.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocationTest {

    @DisplayName("객체 생성 및 비교")
    @Test
    void create() {
        Location location = Location.of(1, 1);
        assertThat(location).isEqualTo(Location.of(1, 1));
    }

    @DisplayName("유효성 검사")
    @Test
    void create_validation() {
        assertThatThrownBy(() -> Location.of(1, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Location.of(9, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Location.of(1, 9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
