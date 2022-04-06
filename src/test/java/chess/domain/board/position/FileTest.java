package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class FileTest {

    @Nested
    class OfTest {

        @Test
        void key에_대응되는_File_인스턴스_반환() {
            File actual = File.of("d");

            assertThat(actual).isEqualTo(File.D);
        }

        @Test
        void key에_대응되는_File_인스턴스가_없으면_예외() {
            assertThatThrownBy(() -> File.of("z"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 행입니다. (범위: a~h)");
        }
    }

    @Test
    void allFilesAscending() {
        List<File> actual = File.allFilesAscending();

        List<File> expected = List.of(
                File.A, File.B, File.C, File.D,
                File.E, File.F, File.G, File.H);

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class ValueDifferenceTest {

        @Test
        void 매개변수로_들어온_파일까지의_상대적인_값_차이를_반환() {
            File from = File.A;
            File to = File.D;

            int actual = from.valueDifference(to);

            assertThat(actual).isEqualTo(3);
        }

        @Test
        void 더_작은_크기의_파일에_대해서는_음수_반환() {
            File from = File.D;
            File to = File.A;

            int actual = from.valueDifference(to);

            assertThat(actual).isEqualTo(-3);
        }

        @Test
        void 같은_파일에_대해서는_영_반환() {
            File from = File.D;
            File to = File.D;

            int actual = from.valueDifference(to);

            assertThat(actual).isZero();
        }
    }

    @Nested
    class OneFileTowardTest {

        @Test
        void 매개변수로_들어온_파일를_향해_한단위_큰_파일_반환() {
            File from = File.A;
            File to = File.D;

            File actual = from.oneFileToward(to);
            File expected = File.B;

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 더_작은_크기의_파일에_대해서는_한단위_작은_파일_반환() {
            File from = File.D;
            File to = File.A;

            File actual = from.oneFileToward(to);
            File expected = File.C;

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 같은_파일에_대해서는_현재_파일_그대로_반환() {
            File from = File.D;
            File to = File.D;

            File actual = from.oneFileToward(to);
            File expected = File.D;

            assertThat(actual).isEqualTo(expected);
        }
    }
}