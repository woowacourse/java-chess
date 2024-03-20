package domain;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("행과 열 정보를 가진 Position 인스턴스를 생성한다.")
    @Test
    void createPositionTest() {
        // Given
        Rank row = Rank.ONE;
        File col = File.A;

        // When
        Position position = new Position(row, col);

        // Then
        assertThat(position.rank()).isEqualTo(row);
        assertThat(position.file()).isEqualTo(col);
    }
}
