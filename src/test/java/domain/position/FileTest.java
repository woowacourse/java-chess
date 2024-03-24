package domain.position;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @Test
    void 존재하지_않은_file명을_찾을_경우_예외가_발생한다() {
        assertThatThrownBy(() -> File.fromName("i"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않은 file입니다.");
    }

    @Test
    void A와_F_사이에_존재하는_file_목록을_반환한다() {
        File source = File.A;
        File target = File.F;

        List<File> betweenFiles = source.between(target);

        assertThat(betweenFiles).containsExactlyElementsOf(List.of(
                File.B, File.C, File.D, File.E
        ));
    }

    @Test
    void F와_A_사이에_존재하는_file_목록을_반환한다() {
        File source = File.F;
        File target = File.A;

        List<File> betweenFiles = source.between(target);

        assertThat(betweenFiles).containsExactlyElementsOf(List.of(
                File.E, File.D, File.C, File.B
        ));
    }
}
