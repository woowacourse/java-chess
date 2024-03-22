package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
