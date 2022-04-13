package chess.domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @DisplayName("같은 파일이면 파일 인덱스가 같다.")
    @Test
    void hasSameFileIdx_true() {
        File actual = File.from("a");
        File expected = File.from("a");

        assertThat(actual.hasSameFileIdx(expected)).isTrue();
    }

}
