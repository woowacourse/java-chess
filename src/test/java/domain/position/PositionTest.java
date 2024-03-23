package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("행과 열 정보를 가진 Position 인스턴스를 생성한다.")
    @Test
    void createPositionTest() {
        // Given
        File file = File.A;
        Rank rank = Rank.ONE;

        // When
        Position position = Position.of(file, rank);

        // Then
        assertThat(position.rank()).isEqualTo(rank);
        assertThat(position.file()).isEqualTo(file);
    }

    @DisplayName("백터 값을 전달하면 새로운 위치의 Position 을 반환한다.")
    @Test
    void addPositionTest() {
        // Given
        Position position = D2;

        // When
        Position newPosition = position.add(UnitVector.UP_RIGHT);

        // Then
        assertThat(newPosition.file()).isEqualTo(File.E);
        assertThat(newPosition.rank()).isEqualTo(Rank.THREE);
    }

    @DisplayName("이전에 생성된 Position 객체가 있으면, 캐싱된 객체를 반환한다.")
    @Test
    void cachedPositionTest() {
        // Given
        File file = File.A;
        Rank rank = Rank.ONE;

        // When
        Position position = Position.of(file, rank);
        Position cachedPosition = Position.of(file, rank);
        Position notCachedPosition = new Position(file, rank);

        // Then
        assertThat(System.identityHashCode(position)).isEqualTo(System.identityHashCode(cachedPosition));
        assertThat(System.identityHashCode(notCachedPosition)).isNotEqualTo(System.identityHashCode(cachedPosition));
    }
}
