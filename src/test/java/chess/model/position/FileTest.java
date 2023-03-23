package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @Nested
    @DisplayName("findFile() 테스트")
    class FindFileMethodTest {

        @ParameterizedTest(name = "{0}을 전달하면 {1}을 반환한다")
        @DisplayName("findFile() 성공 테스트")
        @MethodSource("chess.helper.arguments.FileArguments#provideValidFindFileArguments")
        void findFile_givenName_thenReturnSameNameFile(final String name, final File expected) {
            // given
            final File actual = File.findFile(name);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "1 ~ 8의 범위를 벗어나는 값 {0}을 전달하면 예외가 발생한다")
        @DisplayName("findFile() 실패 테스트")
        @ValueSource(strings = {"I", "Z"})
        void findFile_givenInvalidName_thenFail(final String name) {
            assertThatThrownBy(() -> File.findFile(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 열입니다.");
        }
    }

    @Test
    @DisplayName("differ()는 다른 File을 전달하면 차이를 반환한다")
    void differ_givenOtherFile_thenReturnDiffer() {
        // given
        final File first = File.A;

        // when
        final int actual = first.differ(File.A);

        // then
        assertThat(actual).isZero();
    }

    @Nested
    @DisplayName("findNextFile() 테스트")
    class FindNextFileMethodTest {

        @ParameterizedTest(name = "주어지는 offer가 {0}일 때 {1}을 반환한다")
        @DisplayName("findNextFile() 성공 테스트")
        @MethodSource("chess.helper.arguments.FileArguments#provideValidFindNextFileArguments")
        void findNextRank_givenOffset_thenReturnNextRank(final int offer, final File expected) {
            // given
            final File e = File.E;

            // when
            final File actual = e.findNextFile(offer);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "File이 {0}일 때 계산 결과가 1 ~ 8의 범위를 넘어서는 offer {1}이 주어지면 예외가 발생한다.")
        @DisplayName("findNextFile() 실패 테스트")
        @MethodSource("chess.helper.arguments.FileArguments#provideInvalidFindNextFileArguments")
        void findNextRank_whenBoundaryRank_givenBoundaryOffset_thenFail(final File file, final int offer) {
            assertThatThrownBy(() -> file.findNextFile(offer))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 열입니다.");
        }
    }
}
