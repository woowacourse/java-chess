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
    @DisplayName("비숍의 움직임 로직 확인")
    void move(final Position position) {
        final Bishop bishop = new Bishop(Color.BLACK, Position.from("c5"));
        bishop.moveToEmpty(position, new Pieces());
        assertTrue(bishop.hasPosition(position));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    @DisplayName("비숍이 움직이는 길에 다른 기물이 있을 때 익셉션을 날리는지 확인")
    void interruptedMove(final Position position) {
        final Bishop bishop = new Bishop(Color.BLACK, Position.from("c5"));
        final List<Position> positions = Arrays.asList(Position.from("b6"), Position.from("b4"), Position.from("d4"), Position.from("d6"));

        assertThatThrownBy(() -> bishop.moveToEmpty(position, blackPieces(positions)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Pieces blackPieces(final List<Position> positions) {
        final Piece[] pieces = new Piece[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            pieces[i] = new Pawn(Color.BLACK, positions.get(i));
        }
        return new Pieces(pieces);
    }
}