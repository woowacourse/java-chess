package chess.model.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    private Square square1;
    private Square square2;
    private List<Square> squareList1;
    private List<Square> squareList2;

    @Test
    void getLeftOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getLeftOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.C, Row._5));
    }

    @Test
    void getLeftOneNeighborNull() {
        square1 = new Square(Column.A, Row._5);
        square2 = Square.getLeftOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getRightOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getRightOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.E, Row._5));
    }

    @Test
    void getRightOneNeighborNull() {
        square1 = new Square(Column.H, Row._5);
        square2 = Square.getRightOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getUpOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getUpOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.D, Row._6));
    }

    @Test
    void getUpOneNeighborNull() {
        square1 = new Square(Column.D, Row._8);
        square2 = Square.getUpOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getDownOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getDownOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.D, Row._4));
    }

    @Test
    void getDownOneNeighborNull() {
        square1 = new Square(Column.D, Row._1);
        square2 = Square.getUpOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getUpperLeftOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.C, Row._6));
    }

    @Test
    void getUpperLeftOneNeighborNull() {
        square1 = new Square(Column.A, Row._8);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.A, Row._7);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.B, Row._8);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getUpperRightOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getUpperRightOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.E, Row._6));
    }

    @Test
    void getUpperRightOneNeighborNull() {
        square1 = new Square(Column.H, Row._8);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.H, Row._7);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.G, Row._8);
        square2 = Square.getUpperLeftOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getLowerLeftOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getLowerLeftOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.C, Row._4));
    }

    @Test
    void getLowerLeftOneNeighborNull() {
        square1 = new Square(Column.A, Row._1);
        square2 = Square.getLowerLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.A, Row._2);
        square2 = Square.getLowerLeftOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.B, Row._1);
        square2 = Square.getLowerLeftOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getLowerRightOneNeighbor() {
        square1 = new Square(Column.D, Row._5);
        square2 = Square.getLowerLeftOneNeighbor(square1);
        assertThat(square2).isEqualTo(new Square(Column.C, Row._4));
    }

    @Test
    void getLowerRightOneNeighborNull() {
        square1 = new Square(Column.H, Row._1);
        square2 = Square.getLowerRightOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.H, Row._2);
        square2 = Square.getLowerRightOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.G, Row._1);
        square2 = Square.getLowerRightOneNeighbor(square1);
        assertThat(square2).isNull();
    }

    @Test
    void getLeftNeighbors() {
        Row row = Row._5;
        square1 = new Square(Column.D, row);
        squareList1 = Square.getLeftNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.A, row),
                new Square(Column.B, row),
                new Square(Column.C, row)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getLeftNeighborsNull() {
        square1 = new Square(Column.A, Row._5);
        squareList1 = Square.getLeftNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getRightNeighbors() {
        Row row = Row._5;
        square1 = new Square(Column.D, row);
        squareList1 = Square.getRightNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.E, row),
                new Square(Column.F, row),
                new Square(Column.G, row),
                new Square(Column.H, row)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getRightNeighborsNull() {
        square1 = new Square(Column.H, Row._5);
        squareList1 = Square.getLeftNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getUpNeighbors() {
        Column column = Column.D;
        square1 = new Square(column, Row._5);
        squareList1 = Square.getUpNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(column, Row._6),
                new Square(column, Row._7),
                new Square(column, Row._8)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getUpNeighborsNull() {
        square1 = new Square(Column.D, Row._8);
        squareList1 = Square.getUpNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getDownNeighbors() {
        Column column = Column.D;
        square1 = new Square(column, Row._5);
        squareList1 = Square.getDownNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(column, Row._1),
                new Square(column, Row._2),
                new Square(column, Row._3),
                new Square(column, Row._4)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getDownNeighborsNull() {
        square1 = new Square(Column.D, Row._1);
        squareList1 = Square.getDownNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getUpperLeftNeighbors() {
        square1 = new Square(Column.D, Row._5);
        squareList1 = Square.getUpperLeftNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.C, Row._6),
                new Square(Column.B, Row._7),
                new Square(Column.A, Row._8)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getUpperLeftNeighborsNull() {
        square1 = new Square(Column.A, Row._8);
        squareList1 = Square.getUpperLeftNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getUpperRightNeighbors() {
        square1 = new Square(Column.D, Row._5);
        squareList1 = Square.getUpperRightNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.E, Row._6),
                new Square(Column.F, Row._7),
                new Square(Column.G, Row._8)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getUpperRightNeighborsNull() {
        square1 = new Square(Column.H, Row._8);
        squareList1 = Square.getUpperRightNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getLowerLeftNeighbors() {
        square1 = new Square(Column.D, Row._5);
        squareList1 = Square.getLowerLeftNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.C, Row._4),
                new Square(Column.B, Row._3),
                new Square(Column.A, Row._2)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getLowerLeftNeighborsNull() {
        square1 = new Square(Column.A, Row._1);
        squareList1 = Square.getLowerLeftNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }

    @Test
    void getLowerRightNeighbors() {
        square1 = new Square(Column.D, Row._5);
        squareList1 = Square.getLowerRightNeighbors(square1);
        squareList2 = Arrays.asList(
                new Square(Column.E, Row._4),
                new Square(Column.F, Row._3),
                new Square(Column.G, Row._2),
                new Square(Column.H, Row._1)
        );
        assertThat(squareList1.containsAll(squareList2)).isTrue();
        assertThat(squareList2.containsAll(squareList1)).isTrue();
    }

    @Test
    void getLowerRightNeighborsNull() {
        square1 = new Square(Column.H, Row._1);
        squareList1 = Square.getLowerRightNeighbors(square1);
        assertThat(squareList1.isEmpty()).isTrue();
    }
}