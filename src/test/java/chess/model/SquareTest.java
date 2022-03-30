package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.board.Square;
import chess.model.strategy.move.Direction;
import chess.model.strategy.move.Distance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SquareTest {

    @ParameterizedTest
    @CsvSource(value = {"a1", "a8", "h1", "h8"})
    void createSquare(String squareName) {
        assertThat(Square.of(squareName)).isInstanceOf(Square.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a0", "a9", "h0", "h9", "i1", "i8", "a11"})
    void invalidCreateSquare(String squareName) {
        assertThatThrownBy(() -> Square.of(squareName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void distinguishSameSquare() {
        Square a1 = Square.of(File.A, Rank.ONE);
        Square anotherA1 = Square.of(File.A, Rank.ONE);

        assertThat(a1).isEqualTo(anotherA1);
    }

    @Test
    void distinguishDifferentSquare() {
        Square a1 = Square.of(File.A, Rank.ONE);
        Square a2 = Square.of(File.A, Rank.TWO);

        assertThat(a1).isNotEqualTo(a2);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1:a8:NORTH", "a8:a1:SOUTH", "a8:h8:EAST", "h8:a8:WEST",
            "a1:h8:NORTHEAST", "h8:a1:SOUTHWEST", "a8:h1:SOUTHEAST", "h1:a8:NORTHWEST",
            "d5:c7:NNW", "d5:b6:WWN", "d5:b4:WWS", "d5:c3:SSW",
            "d5:e3:SSE", "d5:f4:EES", "d5:f6:EEN", "d5:e7:NNE"}, delimiter = ':')
    void findDirection(String source, String target, Direction direction) {
        Square sourceSquare = Square.of(source);
        Square targetSquare = Square.of(target);
        assertThat(sourceSquare.findDirection(targetSquare)).isEqualTo(direction);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1:b8:NORTH", "b8:a1:SOUTH", "a8:h7:EAST", "h7:a8:WEST",
            "a1:c8:NORTHEAST", "c8:a1:SOUTHWEST", "a8:c1:SOUTHEAST", "c1:a8:NORTHWEST",
            "d5:g7:NNW", "d5:h6:WWN", "d5:h4:WWS", "d5:g3:SSW",
            "d5:a3:SSE", "d5:a4:EES", "d5:a6:EEN", "d5:a7:NNE"}, delimiter = ':')
    void invalidFindDirection(String source, String target) {
        Square sourceSquare = Square.of(source);
        Square targetSquare = Square.of(target);
        assertThatThrownBy(() -> sourceSquare.findDirection(targetSquare))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 방향입니다.");
    }

    @Test
    void tryToMove() {
        Square a1 = Square.of("a1");
        Distance distanceToA2 = a1.getDistance(Square.of("a2"));
        assertThat(distanceToA2).isEqualTo(Distance.of(1));
    }
}
