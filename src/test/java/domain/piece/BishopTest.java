package domain.piece;

import domain.position.Position;
import org.assertj.core.util.Lists;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("f8")),
                Arguments.of(Position.from("a7")),
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("e3"))
        );
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        Bishop bishop = new Bishop(Color.BLACK, Position.from("c5"));
        bishop.move(position, new Pieces());
        assertTrue(bishop.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void interruptedMove(Position position) {
        Bishop bishop = new Bishop(Color.BLACK, Position.from("c5"));
        List<Position> positions = Arrays.asList(Position.from("b6"), Position.from("b4"), Position.from("d4"), Position.from("d6"));

        assertThatThrownBy(() -> bishop.move(position, blackPieces(positions)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Pieces blackPieces(List<Position> positions) {
        Piece[] pieces = new Piece[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            pieces[i] = new Pawn(Color.BLACK, positions.get(i));
        }
        return new Pieces(pieces);
    }
}