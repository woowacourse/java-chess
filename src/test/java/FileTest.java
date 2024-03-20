import domain.piece.point.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {
    @Test
    @DisplayName("다음 파일을 가져온다.")
    void get_next_file() {
        File file = File.F;

        final var sut = file.next();

        assertThat(sut).isEqualTo(File.G);
    }

    @Test
    @DisplayName("그전 파일을 가져온다.")
    void get_prev_file() {
        File file = File.D;

        final var sut = file.prev();

        assertThat(sut).isEqualTo(File.C);
    }

    @Test
    @DisplayName("현재 파일이 끝인지 확인한다.")
    void check_in_last_bound() {
        Set<File> set = EnumSet.of(File.A, File.H);

        set.forEach(file -> assertThat(file.isAtBoundary()).isTrue());
    }
}
