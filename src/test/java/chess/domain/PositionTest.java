package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("칸을 생성한다.")
    void create() {
        char file = 'a';
        int rank = 1;

        assertThat(Position.of(file, rank)).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,b,1,0", "a,1,b,2,1", "a,1,b,3,2", "a,1,c,2,0.5"})
    @DisplayName("타겟 위치와의 기울기를 구한다.")
    void calculateGradient(char file1, int rank1, char file2, int rank2, Double expectedGradient) {
        Position position1 = Position.of(file1, rank1);
        Position position2 = Position.of(file2, rank2);

        double actualGradient = position1.calculateGradient(position2);

        assertThat(actualGradient).isEqualTo(expectedGradient);
    }

    @Test
    @DisplayName("기울기가 무한대가 되는 경우 정상적으로 계산되는지 테스트한다.")
    void calculateInfiniteGradient() {
        Position position1 = Position.of('a', 1);
        Position position2 = Position.of('a', 2);

        double actualGradient = position1.calculateGradient(position2);

        assertThat(actualGradient).isInfinite();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,b,1,1", "a,1,a,2,1"})
    @DisplayName("타겟 위치와의 정수 거리를 구한다.")
    void calculateIntegerDistance(char file1, int rank1, char file2, int rank2, Double expectedDistance) {
        Position position1 = Position.of(file1, rank1);
        Position position2 = Position.of(file2, rank2);

        double actualDistance = position1.calculateDistance(position2);

        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @Test
    @DisplayName("타겟 위치와의 제곱근 거리를 구한다.")
    void calculateDoubleDistance() {
        char file1 = 'a';
        int rank1 = 1;
        char file2 = 'b';
        int rank2 = 2;
        Position position1 = Position.of(file1, rank1);
        Position position2 = Position.of(file2, rank2);

        double actualDistance = position1.calculateDistance(position2);

        double expectedDistance = Math.sqrt(Math.pow(file1 - file2, 2) + Math.pow(rank1 - rank2, 2));
        assertThat(actualDistance).isEqualTo(expectedDistance);
    }
}
