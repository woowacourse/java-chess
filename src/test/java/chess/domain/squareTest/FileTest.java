package chess.domain.squareTest;

import chess.domain.square.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileTest {
    @Test
    @DisplayName("File이 같은 객체를 실제 같은 File로 인식하는지 확인")
    void checkSameFile() {
        File file = File.of(2);
        assertThat(file).isEqualTo(File.of('b'));
    }

    @Test
    @DisplayName("범위 바깥의 File.of를 부를 때 exception 확인")
    void checkException() {
        assertThatThrownBy(() -> File.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된");
    }

    @Test
    @DisplayName("File이 증가했을 때 범위 안에 있는지 체크")
    void validateIncrementBy() {
        File file = File.of('c');
        assertThat(file.validateIncrementNumber(6)).isFalse();
        assertThat(file.validateIncrementNumber(5)).isTrue();
    }
}
