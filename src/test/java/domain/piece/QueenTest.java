package domain.piece;

import domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("a5")),
                Arguments.of(Position.from("h5")),
                Arguments.of(Position.from("c1")),
                Arguments.of(Position.from("c7")),
                Arguments.of(Position.from("f8")),
                Arguments.of(Position.from("a7")),
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("e3"))
        );
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        Queen queen = new Queen(Color.BLACK, Position.from("c5"));
        queen.move(position, new Pieces());
        assertTrue(queen.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void interruptedMove(Position position) {
        Queen queen = new Queen(Color.BLACK, Position.from("c5"));
        List<Position> positions = Arrays.asList(Position.from("b5"), Position.from("e5"), Position.from("c2"), Position.from("c6"),
                Position.from("b6"), Position.from("b4"), Position.from("d4"), Position.from("d6"));

        assertThatThrownBy(() -> queen.move(position, blackPieces(positions)))
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