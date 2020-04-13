package chess.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    @DisplayName("Col 비교 테스트 : 참")
    void isSame() {
        Location location = new Location(Row.of(1), Col.of('b'));
        Location after = new Location(Row.of(3), Col.of('b'));

        assertThat(location.isSameCol(after)).isTrue();
    }

    @Test
    @DisplayName("Col 비교 테스트 : 거짓")
    void isSame2() {
        Location location = new Location(Row.of(1), Col.of('b'));
        Location after = new Location(Row.of(3), Col.of('c'));

        assertThat(location.isSameCol(after)).isFalse();
    }
}