package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
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
    @DisplayName("룩의 움직임 로직확인")
    void move(Position position) {
        Rook rook = new Rook(Color.BLACK, Position.from("c5"));
        rook.moveToEmpty(position, new Pieces());
        assertTrue(rook.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    @DisplayName("룩이 움직이는 길에 다른 기물이 있을 때 익셉션을 날리는지 확인")
    void interruptedMove(Position position) {
        Rook rook = new Rook(Color.BLACK, Position.from("c5"));
        List<Position> positions = Arrays.asList(Position.from("b5"), Position.from("e5"), Position.from("c2"), Position.from("c6"));

        assertThatThrownBy(() -> rook.moveToEmpty(position, blackPieces(positions)))
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