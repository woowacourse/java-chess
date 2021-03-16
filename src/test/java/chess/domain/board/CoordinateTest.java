package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("Coordinate는 File과 Rank를 필드로 가지는 VO다")
    @Test
    void compareCoordinateEquality() {
        Coordinate coordinate = new Coordinate(File.A, Rank.EIGHT);
        Coordinate targetCoordinate = new Coordinate(File.A, Rank.EIGHT);

        assertThat(coordinate).isEqualTo(targetCoordinate);
    }
}