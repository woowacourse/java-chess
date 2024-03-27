package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SlidingPieceTest {
    @ParameterizedTest
    @MethodSource("provideSlidingPieceAndMovementAndPath")
    void 슬라이딩_기물은_시작점과_도착점_사이의_경로를_반환한다(Piece piece, Movement movement, List<Position> positions) {
        assertThat(piece.getIntermediatePositions(movement))
                .isEqualTo(positions);
    }

    private static Stream<Arguments> provideSlidingPieceAndMovementAndPath() {
        return Stream.of(
                Arguments.of(Bishop.from(Color.BLACK),
                        new Movement(Position.of(1, 1), Position.of(4, 4)),
                        List.of(Position.of(2, 2), Position.of(3, 3))),
                Arguments.of(Rook.from(Color.BLACK),
                        new Movement(Position.of(1, 1), Position.of(1, 4)),
                        List.of(Position.of(1, 2), Position.of(1, 3))),
                Arguments.of(Queen.from(Color.BLACK),
                        new Movement(Position.of(1, 1), Position.of(4, 4)),
                        List.of(Position.of(2, 2), Position.of(3, 3)))
        );
    }
}
