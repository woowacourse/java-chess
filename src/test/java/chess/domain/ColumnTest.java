package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ColumnTest {

    @Test
    void getOrderedColumns_메서드로_인덱스_오름차순으로_정렬() {
        //given
        List<Column> expected = List.of(Column.A, Column.B, Column.C, Column.D,
                Column.E, Column.F, Column.G, Column.H);
        //when
        List<Column> actual = Column.getOrderedColumns();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findColumnByValue() {
        //given
        Column expected = Column.G;

        //when
        Column actualByValue = Column.findColumnByValue("g");

        //then
        Assertions.assertEquals(expected, actualByValue);
    }

    @Test
    void findColumnByIndex() {
        //given
        Column expected = Column.G;

        //when
        Column actualByIndex = Column.findColumnByIndex(7);

        //then
        Assertions.assertEquals(expected, actualByIndex);
    }
}
