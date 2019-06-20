package chess.model.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    private Square square1;
    private Square square2;

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
        square2 = Square.getDownOneNeighbor(square1);
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
        square2 = Square.getUpperRightOneNeighbor(square1);
        assertThat(square2).isNull();

        square1 = new Square(Column.G, Row._8);
        square2 = Square.getUpperRightOneNeighbor(square1);
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
}