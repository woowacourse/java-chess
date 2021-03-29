package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {
    private Bishop bishop;

    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("f8")),
                Arguments.of(Position.from("a7")),
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("e3"))
        );
    }

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.BLACK, Position.from("c5"));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        bishop.moveToEmpty(position, new Pieces());
        assertTrue(bishop.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void interruptedMove(Position position) {
        List<Position> positions = Arrays.asList(Position.from("b6"), Position.from("b4"), Position.from("d4"), Position.from("d6"));

        assertThatThrownBy(() -> bishop.moveToEmpty(position, blackPieces(positions)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Pieces blackPieces(List<Position> positions) {
        Piece[] pieces = new Piece[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            pieces[i] = new Pawn(Color.BLACK, positions.get(i));
        }
        return new Pieces(pieces);
    }

    @Test
    @DisplayName("비숍 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = bishop.movablePosition(position);
        assertThat(positions).contains(
                Position.from("b4"),
                Position.from("a3"),
                Position.from("d4"),
                Position.from("e3"),
                Position.from("f2"),
                Position.from("g1"),
                Position.from("b6"),
                Position.from("a7"),
                Position.from("d6"),
                Position.from("e7"),
                Position.from("f8")
                );
    }
}