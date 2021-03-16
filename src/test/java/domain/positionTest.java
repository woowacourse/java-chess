package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class positionTest {
    @Test
    void createPosition() {
        assertThatCode(() -> new Position(Column.A, Row.ONE))
                .doesNotThrowAnyException();
    }
}
