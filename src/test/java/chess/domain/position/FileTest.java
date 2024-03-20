package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("파일")
class FileTest {

    @DisplayName("파일은 인덱스만큼 수평 이동한다.")
    @Test
    void moveHorizontalByIndex() {
        // given
        File file = File.d;
        int index = -3;

        // when
        File actual = file.moveHorizontal(index);

        // then
        assertThat(actual).isEqualTo(File.a);
    }

    @DisplayName("파일은 수평 이동 시 범위를 벗어나면 예외가 발생한다.")
    @Test
    void forwardByIndexException() {
        // given
        File file = File.a;
        int index = -1;

        // when & then
        assertThatThrownBy(() -> file.moveHorizontal(index)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}