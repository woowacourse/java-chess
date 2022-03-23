package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FileTest {

    @DisplayName("문자열을 받아 알맞은 파일을 반환한다.")
    @ParameterizedTest
    @CsvSource({"a,A", "b,B", "c,C", "d,D", "e,E", "f,F", "g,G", "h,H"})
    void 문자열을_받아_알맞은_파일을_반환한다(String value, File file) {
        assertThat(File.of(value)).isEqualTo(file);
    }

    @DisplayName("잘못된 문자열이 들어온 경우 예외가 발생한다.")
    @Test
    void 잘못된_문자열이_들어온_경우_예외가_발생한다() {
        assertThatThrownBy(() -> File.of("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 파일입니다.");
    }
}
