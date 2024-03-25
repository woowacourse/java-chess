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

    @Test
    void 다른_file과_사이에_있는_file을_찾을_때_자신의_file이_더_작은_경우_오름차순으로_반환한다() {
        File file = File.fromName("d");
        File other = File.fromName("h");

        assertThat(file.findFilesBetween(other)).containsExactly(File.E, File.F, File.G);
    }

    @Test
    void 다른_file과_사이에_있는_file을_찾을_때_자신의_file이_더_큰_경우_내림차순으로_반환한다() {
        File file = File.fromName("h");
        File other = File.fromName("d");

        assertThat(file.findFilesBetween(other)).containsExactly(File.G, File.F, File.E);
    }
}
