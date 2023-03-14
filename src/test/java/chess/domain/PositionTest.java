package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.File.A;
import static chess.domain.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {

    @Test
    @DisplayName("File과 Rank정보로 위치를 만든다")
    void initTest() {
        assertThatNoException().isThrownBy(() -> new Position(A, ONE));
    }

}
