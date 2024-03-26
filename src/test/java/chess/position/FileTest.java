package chess.position;

import static chess.domain.position.File.a;
import static chess.domain.position.File.b;
import static chess.domain.position.File.c;
import static chess.domain.position.File.d;
import static chess.domain.position.File.e;
import static chess.domain.position.File.f;
import static chess.domain.position.File.g;
import static chess.domain.position.File.h;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("FIle과 FIle간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        File fileA = a;
        File fileB = b;

        assertThat(fileA.calculateDifferenceTo(fileB)).isEqualTo(1);
    }

    @Test
    @DisplayName("File과 FIle간 증가하는 루트를 구할 수 있다")
    void should_find_incline_route() {
        File from = a;
        File to = h;

        List<File> fileRouteToTargetFile = from.findFileRouteToTargetFile(to);

        assertThat(fileRouteToTargetFile).containsOnly(b, c, d, e, f, g);
    }

    @Test
    @DisplayName("File과 FIle간 감소하는 루트를 구할 수 있다")
    void should_find_decline_route() {
        File from = h;
        File to = a;

        List<File> fileRouteToTargetFile = from.findFileRouteToTargetFile(to);

        assertThat(fileRouteToTargetFile).containsOnly(b, c, d, e, f, g);
    }
}
