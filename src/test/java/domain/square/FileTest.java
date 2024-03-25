package domain.square;

import chess.domain.square.File;
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

    @DisplayName("순서를 받아 열을 찾는다.")
    @Test
    void findTest() {
        Assertions.assertAll(
                () -> assertThat(File.find(1)).isEqualTo(File.A),
                () -> assertThat(File.find(2)).isEqualTo(File.B),
                () -> assertThat(File.find(3)).isEqualTo(File.C),
                () -> assertThat(File.find(4)).isEqualTo(File.D),
                () -> assertThat(File.find(5)).isEqualTo(File.E),
                () -> assertThat(File.find(6)).isEqualTo(File.F),
                () -> assertThat(File.find(7)).isEqualTo(File.G),
                () -> assertThat(File.find(8)).isEqualTo(File.H)
        );
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
