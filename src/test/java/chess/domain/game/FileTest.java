package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class FileTest {

    private static Stream<Arguments> generatePath() {
        return Stream.of(
                Arguments.of(File.A, File.D, List.of(File.B, File.C)),
                Arguments.of(File.E, File.E, List.of()),
                Arguments.of(File.H, File.A, List.of(File.G, File.F, File.E, File.D, File.C, File.B)));
    }

    @ParameterizedTest
    @CsvSource(value = {"a, A", "b, B", "c, C", "d, D", "e, E", "f, F", "g, G", "h, H"})
    void from_을_통해서_Char_로_생성할_수_있다(char input, File expected) {
        //given
        //when
        File result = File.from(input);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, A", "2, B", "3, C", "4, D", "5, E", "6, F", "7, G", "8, H"})
    void from_을_통해서_file_order_를_바탕으로_생성_가능(int input, File expected) {
        //given
        //when
        File result = File.from(input);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, B, 1", "B, C, 1", "C, D, 1", "D, E, 1", "E, F, 1", "F, G, 1", "G, H, 1", "H, A, -7"})
    void getDifference(File file, File other, int expected) {
        //given
        //when
        int result = file.getDifference(other);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource("generatePath")
    void createPath(File origin, File destination, List<File> expected) {
        //given
        //when
        List<File> result = origin.createPath(destination);

        //then
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }
}
