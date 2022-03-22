package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    void createSquare() {
        Square a1 = new Square(File.A, Rank.ONE);
        assertThat(a1).isInstanceOf(Square.class);
    }

    @Test
    void distinguishSquare() {
        Square a1 = new Square(File.A, Rank.ONE);
        Square anotherA1 = new Square(File.A, Rank.ONE);

        assertThat(a1).isEqualTo(anotherA1);
    }
}
