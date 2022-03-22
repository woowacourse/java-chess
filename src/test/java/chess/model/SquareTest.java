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
    void distinguishSameSquare() {
        Square a1 = new Square(File.A, Rank.ONE);
        Square anotherA1 = new Square(File.A, Rank.ONE);

        assertThat(a1).isEqualTo(anotherA1);
    }

    @Test
    void distinguishDifferentSquare() {
        Square a1 = new Square(File.A, Rank.ONE);
        Square a2 = new Square(File.A, Rank.TWO);

        assertThat(a1).isNotEqualTo(a2);
    }
}
