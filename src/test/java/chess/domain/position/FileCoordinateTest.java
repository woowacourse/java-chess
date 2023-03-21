package chess.domain.position;

import static chess.domain.position.FileCoordinate.B;
import static chess.domain.position.FileCoordinate.C;
import static chess.domain.position.FileCoordinate.D;
import static chess.domain.position.FileCoordinate.E;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FileCoordinateTest {

    @Test
    void 파일과_파일_사이의_좌표를_반환한다() {
        FileCoordinate a = FileCoordinate.A;
        FileCoordinate f = FileCoordinate.F;

        assertThat(a.betweenFiles(f)).containsExactly(B, C, D, E);
    }
}
