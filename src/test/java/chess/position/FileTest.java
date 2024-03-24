package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.FileDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("FIle과 FIle간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        File fileA = File.a;
        File fileB = File.b;

        FileDifference expectedDistance = fileA.calculateDifferenceTo(fileB);

        assertThat(expectedDistance).isEqualTo(new FileDifference(1));
    }
}
