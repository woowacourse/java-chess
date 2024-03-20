package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @DisplayName("가장 높은 순서 값을 찾는다.")
    @Test
    void maxTest() {
        assertThat(File.max()).isEqualTo(7);
    }

    @DisplayName("순서를 받아 열을 찾는다.")
    @Test
    void findTest() {
        Assertions.assertAll(
                () -> assertThat(File.find(0)).isEqualTo(File.FIRST),
                () -> assertThat(File.find(1)).isEqualTo(File.SECOND),
                () -> assertThat(File.find(2)).isEqualTo(File.THIRD),
                () -> assertThat(File.find(3)).isEqualTo(File.FORTH),
                () -> assertThat(File.find(4)).isEqualTo(File.FIFTH),
                () -> assertThat(File.find(5)).isEqualTo(File.SIXTH),
                () -> assertThat(File.find(6)).isEqualTo(File.SEVENTH),
                () -> assertThat(File.find(7)).isEqualTo(File.EIGHTH)
        );
    }
}
