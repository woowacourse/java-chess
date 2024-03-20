package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileTest {
    @Test
    void 존재하지_않은_file명을_찾을_경우_예외가_발생한다() {
        assertThatThrownBy(() -> File.fromName("i"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않은 file입니다.");
    }

    @Test
    void 존재하는_file명을_찾을_경우_해당_file을_반환한다() {
        File file = File.fromName("a");

        assertThat(file).isEqualTo(File.A);
    }

    @Test
    void 현재_file의_다음_file을_반환한다() {
        File file = File.fromName("a");

        assertThat(file.next()).isEqualTo(File.B);
    }

    @Test
    void file_H의_다음_file을_호출하면_예외가_발생한다() {
        File file = File.fromName("h");

        assertThatThrownBy(file::next)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어난 file입니다.");
    }

    @Test
    void 현재_file의_이전_file을_반환한다() {
        File file = File.fromName("h");

        assertThat(file.prev()).isEqualTo(File.G);
    }

    @Test
    void File_A의_이전_file을_호출하면_예외가_발생한다() {
        File file = File.fromName("a");

        assertThatThrownBy(file::prev)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어난 file입니다.");
    }
}
