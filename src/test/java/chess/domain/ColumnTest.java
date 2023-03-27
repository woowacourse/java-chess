package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColumnTest {

    @Test
    void getOrderedColumns_메서드로_인덱스_오름차순으로_정렬() {
        //given
        List<Column> expected = List.of(Column.A, Column.B, Column.C, Column.D,
                Column.E, Column.F, Column.G, Column.H);
        //when
        List<Column> actual = Column.getOrderedColumns();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findColumnByValue_메서드_테스트() {
        //given
        Column expected = Column.G;

        //when
        Column actualByValue = Column.findColumnByValue("g");

        //then
        assertEquals(expected, actualByValue);
    }

    @Test
    void findColumnByIndex_메서드_테스트() {
        //given
        Column expected = Column.G;

        //when
        Column actualByIndex = Column.findColumnByIndex(7);

        //then
        assertEquals(expected, actualByIndex);
    }
}
