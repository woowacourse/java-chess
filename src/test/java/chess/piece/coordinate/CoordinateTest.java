package chess.piece.coordinate;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoordinateTest {
    @Test
    void 좌표는_row와_column값을_받아서_생성한다() {
        Coordinate coordinate = Coordinate.createCoordinate("1", "a");
        assertThat(coordinate).isNotNull();
    }
}
