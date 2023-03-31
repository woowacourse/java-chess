package chess.domain.board.coordinate;

import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.CoordinateFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoordinateTest {
    @Test
    void 좌표는_row와_column값을_받아서_생성한다() {
        assertThat(A_ONE).isNotNull();
    }
    
    @Test
    void Column_index_반환() {
        int columnIndex = C_FOUR.columnIndex();
        assertThat(columnIndex).isEqualTo(2);
    }
    
    @Test
    void 현재_row가_해당_row보다_이하인지_확인() {
        assertAll(
                () -> assertThat(C_FOUR.isRowNumLessOrEqualTo(4)).isTrue(),
                () -> assertThat(C_FOUR.isRowNumLessOrEqualTo(5)).isTrue()
        );
    }
    
    @Test
    void 현재_row가_해당_row보다_이상인지_확인() {
        assertAll(
                () -> assertThat(C_FOUR.isRowNumOverOrEqualTo(4)).isTrue(),
                () -> assertThat(C_FOUR.isRowNumOverOrEqualTo(3)).isTrue()
        );
    }
    
    @Test
    void 현재_row가_해당_row와_같은지_확인() {
        assertThat(C_FOUR.isSameRow(Row.FOUR)).isTrue();
    }
    
    @Test
    void 다음_좌표_반환() {
        Coordinate nextCoordinate = A_FOUR.nextCoordinate(Direction.EAST_EAST_NORTH);
        assertThat(nextCoordinate).isEqualTo(C_FIVE);
    }
    
    @Test
    void 다음_좌표로_이동할_수_있는지_확인() {
        boolean canMove = A_FOUR.canMove(Direction.EAST_EAST_NORTH);
        assertThat(canMove).isTrue();
    }
    
    @Test
    void 현재_row와_direction의_row를_합친_row_사이의_row값_반환() {
        Coordinate betweenRow = A_FOUR.betweenRow(2);
        assertThat(betweenRow).isEqualTo(A_FIVE);
    }
    
    @Test
    void 마지막_column인지_확인() {
        assertAll(
                () -> assertThat(A_FOUR.isLastColumn()).isFalse(),
                () -> assertThat(H_FOUR.isLastColumn()).isTrue()
        );
    }
}
