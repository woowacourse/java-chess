package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("행과 열 정보를 가진 Position 인스턴스를 생성한다.")
    @Test
    void createPositionTest() {
        // Given
        Row row = Row.A;
        Column col = Column.ONE;

        // When
        Position position = new Position(row, col);

        // Then
        assertThat(position.row()).isEqualTo(row);
        assertThat(position.column()).isEqualTo(col);
    }
}
