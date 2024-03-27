package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("도착 파일이 시작 파일보다 우측이라면 사이 파일을 좌측부터 우측 순서대로 반환한다.")
    void betweenFiles_right() {
        File source = File.A;
        File target = File.E;

        List<File> files = source.betweenFiles(target);

        assertThat(files).containsOnly(File.B, File.C, File.D);
    }

    @Test
    @DisplayName("도착 파일이 시작 파일보다 좌측이라면 사이 파일을 우측부터 좌측 순서대로 반환한다.")
    void betweenFiles_left() {
        File source = File.E;
        File target = File.A;

        List<File> files = source.betweenFiles(target);

        assertThat(files).containsOnly(File.D, File.C, File.B);
    }

    @Test
    @DisplayName("두 파일이 같다면 참을 반환한다.")
    void isSame_Files_True() {
        File source = File.A;
        File target = File.A;

        assertThat(source.isSame(target)).isTrue();
    }

    @Test
    @DisplayName("두 파일이 같지 않다면 거짓을 반환한다.")
    void isSame_Files_False() {
        File source = File.A;
        File target = File.E;

        assertThat(source.isSame(target)).isFalse();
    }
}
