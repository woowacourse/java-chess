package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    @DisplayName("올바른 File을 찾을 수 있다")
    void should_find_file(char fileName) {

        assertThat(File.of(fileName).name()).isEqualTo(String.valueOf(fileName));
    }

    @ParameterizedTest
    @CsvSource({"1,a", "2,b", "3,c", "4,d", "5,e", "6,f", "7,g", "8,h"})
    @DisplayName("올바른 File을 찾을 수 있다")
    void should_find_file_from_value(int fileValue, char fileName) {

        assertThat(File.of(fileValue)).isEqualTo(File.of(fileName));
    }

    @Test
    @DisplayName("File간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        File from = File.a;
        File to = File.b;

        FileDifference expectedDistance = from.calculateDifference(to);

        assertThat(expectedDistance).isEqualTo(new FileDifference(1));
    }

    @Test
    @DisplayName("File간 경로를 만들 수 있다.")
    void should_make_route() {
        File from = File.a;
        File to = File.d;

        assertThat(from.getFileRoute(to)).containsExactly(File.b, File.c);
    }

    @Test
    @DisplayName("같은 File간의 경로는 자기 자신만 포함한다.")
    void should_make_route_when_same_file() {
        File from = File.a;
        File to = File.a;

        assertThat(from.getFileRoute(to)).containsExactly(File.a);
    }
}
