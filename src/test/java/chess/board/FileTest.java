package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("입력받은 값이 더해진 File를 반환한다.")
    void getAddedFile_success() {
        // given
        final File file = File.A;

        // when
        final File addedFile = file.getAddedFile(2);

        // then
        assertThat(addedFile).isSameAs(File.C);
    }

    @Test
    @DisplayName("입력받은 값이 더해진 File이 존재하지 않으면 예외를 던진다.")
    void getAddedFile_fail() {
        // given
        final File file = File.D;

        // when, then
        assertThatThrownBy(() -> file.getAddedFile(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 더한 파일 값이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("대상 File과의 값 차이를 반환한다.")
    // TODO: 2023/03/15 테스트 케이스 추가(Parameterized Test)
    void getValueDiff() {
        // given
        File file = File.A;
        File targetFile = File.E;

        // when
        final int valueDiff = file.getValueDiff(targetFile);

        // then
        assertThat(valueDiff).isEqualTo(4);
    }

    @Test
    @DisplayName("대상 File과의 값 차이에 따른 수평 좌표 값을 반환한다.")
    void getValuePoint() {
        // given
        File file = File.A;
        File targetFile = File.E;

        // when
        final int valuePoint = file.getValuePoint(targetFile);

        // then
        assertThat(valuePoint).isEqualTo(1);
    }
}
