package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("파일")
class FileTest {

    @DisplayName("파일은 인덱스만큼 수평 이동한다.")
    @Test
    void moveHorizontalByIndex() {
        // given
        File file = File.D;
        int index = -3;

        // when
        File actual = file.moveHorizontal(index);

        // then
        assertThat(actual).isEqualTo(File.A);
    }

    @DisplayName("파일은 수평 이동 시 범위를 벗어나면 예외가 발생한다.")
    @Test
    void forwardByIndexException() {
        // given
        File file = File.A;
        int index = -1;

        // when & then
        assertThatThrownBy(() -> file.moveHorizontal(index)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("파일은 인덱스에 따라 정렬된 결과를 반환한다.")
    void filesSorted() {
        // given & when & then
        assertThat(File.sorted()).isEqualTo(List.of(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H));
    }
}
