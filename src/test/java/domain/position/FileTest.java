package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    @DisplayName("두 파일 간 방향이 오른쪽이면 참을 반환한다.")
    void isRight_True() {
        File source = File.A;
        File target = File.E;

        boolean isRight = source.isRight(target);

        assertThat(isRight).isTrue();
    }

    @Test
    @DisplayName("두 파일 간 방향이 왼쪽이면 참을 반환한다.")
    void isLeft_True() {
        File source = File.E;
        File target = File.A;

        boolean isLeft = source.isLeft(target);

        assertThat(isLeft).isTrue();
    }

    @Test
    @DisplayName("두 파일이 같으면 거짓을 반환한다.")
    void isRight_False_isLeft_False() {
        File source = File.D;
        File target = File.D;

        boolean isRight = source.isRight(target);
        boolean isLeft = source.isLeft(target);

        assertAll(() -> {
            assertThat(isRight).isFalse();
            assertThat(isLeft).isFalse();
        });
    }

    @Test
    @DisplayName("두 파일 사이의 모든 파일을 출발부터 도착까지 순서대로 반환한다.")
    void betweenFiles() {
        File source = File.A;
        File target = File.E;

        List<File> files = source.betweenFiles(target);

        assertThat(files).containsExactly(File.B, File.C, File.D);
    }

    @Test
    @DisplayName("두 파일 사이의 모든 파일을 출발부터 도착까지 순서대로 반환한다. - 역순")
    void betweenFiles_reversed() {
        File source = File.E;
        File target = File.A;

        List<File> files = source.betweenFiles(target);

        assertThat(files).containsExactly(File.D, File.C, File.B);
    }
}
