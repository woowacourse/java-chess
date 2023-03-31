package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.File.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FileTest {

    @ParameterizedTest
    @CsvSource({"A, 3", "B, 2", "D, 0", "H, 4"})
    void 거리를_계산한다(final File otherFile, final int expectedDistance) {
        //given
        final File file = D;

        //when
        final int actualDistance = file.calculateDistance(otherFile);

        //then
        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @Nested
    class 두_파일_사이의_파일들을_구한다 {

        @Test
        void 오름차순_파일들을_반환한다() {
            //given
            final File startFile = A;
            final File endFile = H;

            //when
            final List<File> betweenFiles = startFile.getFilesTo(endFile);

            //then
            assertThat(betweenFiles).containsExactly(B, C, D, E, F, G);
        }

        @Test
        void 내림차순_파일들을_반환한다() {
            //given
            final File startFile = H;
            final File endFile = A;

            //when
            final List<File> betweenFiles = startFile.getFilesTo(endFile);

            //then
            assertThat(betweenFiles).containsExactly(G, F, E, D, C, B);
        }
    }
}
