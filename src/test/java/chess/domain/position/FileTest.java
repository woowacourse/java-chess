package chess.domain.position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @DisplayName("가장 높은 순서 값을 찾는다.")
    @Test
    void maxTest() {
        assertThat(File.max()).isEqualTo(8);
    }

    @DisplayName("source order가 작을 때 source 위치와 target 위치 사이의 File 리스트를 찾는다.")
    @Test
    void findBetweenAscTest() {
        File currentFile = File.A;
        File targetFile = File.D;

        List<File> files = currentFile.findBetween(targetFile);

        assertThat(files).containsExactly(File.B, File.C);
    }

    @DisplayName("source order가 클 때 source 위치와 target 위치 사이의 File 리스트를 찾는다.")
    @Test
    void findBetweenDescTest() {
        File currentFile = File.D;
        File targetFile = File.A;

        List<File> files = currentFile.findBetween(targetFile);

        assertThat(files).containsExactly(File.C, File.B);
    }
}
