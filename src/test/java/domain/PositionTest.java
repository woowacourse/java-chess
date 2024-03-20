package domain;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import domain.position.UnitVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("행과 열 정보를 가진 Position 인스턴스를 생성한다.")
    @Test
    void createPositionTest() {
        // Given
        File file = File.A;
        Rank rank = Rank.ONE;

        // When
        Position position = new Position(file, rank);

        // Then
        assertThat(position.rank()).isEqualTo(rank);
        assertThat(position.file()).isEqualTo(file);
    }

    @DisplayName("백터 값을 넘기면 새로운 위치의 Position을 반환한다.")
    @Test
    void addPositionTest() {
        // Given
        Position position = new Position(File.D, Rank.TWO);

        // When
        Position newPosition = position.add(UnitVector.UP_RIGHT);

        // Then
        assertThat(newPosition.file()).isEqualTo(File.E);
        assertThat(newPosition.rank()).isEqualTo(Rank.THREE);
    }
}
