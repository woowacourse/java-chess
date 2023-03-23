package chess.domain.piece.coordinate;

import chess.domain.distance.Distances;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoordinateTest {
    @Test
    void 좌표는_row와_column값을_받아서_생성한다() {
        Coordinate coordinate = new Coordinate('a', 1);
        assertThat(coordinate).isNotNull();
    }
    
    @Test
    void 목적지_좌표로_향하는_다음_Coordinate_반환() {
        Coordinate coordinate = new Coordinate('a', 4);
        assertThat(coordinate.coordinateOneStepFor(new Coordinate('c', 1))).isEqualTo(new Coordinate('b', 3));
    }
    
    @Test
    void Column_index_반환() {
        Coordinate coordinate = new Coordinate('c', 4);
        int columnIndex = coordinate.columnIndex();
        assertThat(columnIndex).isEqualTo(2);
    }
    
    @Test
    void 다른_좌표와의_거리_반환() {
        Coordinate coordinate = new Coordinate('c', 4);
        assertThat(coordinate.subtractCoordinate(new Coordinate('a', 7))).isEqualTo(new Distances(2, -3));
    }
    
    @Test
    void 현재_row가_해당_row보다_이하인지_확인() {
        Coordinate coordinate = new Coordinate('c', 4);
        assertAll(
                () -> assertThat(coordinate.isRowNumLessOrEqualTo(4)).isTrue(),
                () -> assertThat(coordinate.isRowNumLessOrEqualTo(5)).isTrue()
        );
    }
    
    @Test
    void 현재_row가_해당_row보다_이상인지_확인() {
        Coordinate coordinate = new Coordinate('c', 4);
        assertAll(
                () -> assertThat(coordinate.isRowNumOverOrEqualTo(4)).isTrue(),
                () -> assertThat(coordinate.isRowNumOverOrEqualTo(3)).isTrue()
        );
    }
    
    @Test
    void 현재_row가_해당_row와_같은지_확인() {
        Coordinate coordinate = new Coordinate('c', 4);
        assertThat(coordinate.isSameRow(Row.FOUR)).isTrue();
    }
}
