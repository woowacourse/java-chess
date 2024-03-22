package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    @DisplayName("FIle과 FIle간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        File fileA = File.a;
        File fileB = File.b;

        FileDifference expectedDistance = fileA.calculateDifference(fileB);

        assertThat(expectedDistance).isEqualTo(new FileDifference(1));
    }
}
