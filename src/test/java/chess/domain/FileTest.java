package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File 클래스")
class FileTest {

    @Nested
    @DisplayName("filesBetween 메서드는")
    class filesBetween {
        @Nested
        @DisplayName("다른 File 객체가 주어지면")
        class with_another_file {
            final File from = File.A;
            final File other = File.D;

            @Test
            @DisplayName("두 파일 사이에 있는 파일들을 반환한다")
            void it_returns_files() {
                List<File> filePath = File.filesBetween(from, other);
                assertThat(filePath).containsOnly(File.B, File.C);
            }
        }
    }

    @Nested
    @DisplayName("distanceTo 메서드는")
    class distanceTo {
        @Nested
        @DisplayName("다른 File 객체가 주어지면")
        class with_another_file {
            final File from = File.A;
            final File other = File.D;

            @Test
            @DisplayName("두 파일간의 거리를 계산한다")
            void it_returns_distance() {
                assertThat(from.distanceTo(other)).isEqualTo(3);
            }
        }
    }
}
