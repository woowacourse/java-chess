package domain.piece;

import domain.position.Position;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("a5")),
                Arguments.of(Position.from("h5")),
                Arguments.of(Position.from("c1")),
                Arguments.of(Position.from("c7"))
        );
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        Rook rook = new Rook(Color.BLACK, Position.from("c5"));
        rook.move(position, Lists.emptyList());
        assertTrue(rook.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void interruptedMove(Position position) {
        Rook rook = new Rook(Color.BLACK, Position.from("c5"));
        List<Position> positions = Arrays.asList(Position.from("b5"), Position.from("e5"), Position.from("c2"), Position.from("c6"));

        assertThatThrownBy(() -> rook.move(position, blackPieces(positions)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Piece> blackPieces(List<Position> positions) {
        return positions.stream()
                        .map(position -> new Pawn(Color.BLACK, position))
                        .collect(Collectors.toList());
    }
}