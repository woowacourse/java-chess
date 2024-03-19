package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("FIle과 FIle간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        File fileB = File.b;
        File fileA = File.a;

        FileDifference expectedDistance = fileB.calculateDifference(fileA);

        assertThat(expectedDistance).isEqualTo(new FileDifference(1));
    }
}
