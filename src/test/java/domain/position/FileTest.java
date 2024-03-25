package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("두 파일 사이의 거리를 구한다.")
    void distance_Files() {
        File source = File.A;
        File target = File.E;

        int distance = source.distance(target);

        assertThat(distance).isEqualTo(4);
    }

    @Test
    @DisplayName("두 파일 사이의 모든 파일을 반환한다.")
    void betweenFiles() {
        File source = File.A;
        File target = File.E;

        List<File> files = source.betweenFiles(target);

        assertThat(files).containsOnly(File.B, File.C, File.D);
    }
}
