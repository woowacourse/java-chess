package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class JumpingPieceTest {
    @ParameterizedTest
    @MethodSource("provideJumpingPiece")
    void 점핑_기물은_시작점과_도착점_사이의_경로로_빈값을_반환한다(Piece piece) {
        Movement movement = new Movement(Position.of(1, 1), Position.of(2, 3));
        assertThat(piece.getIntermediatePositions(movement))
                .isEqualTo(List.of());
    }

    private static Stream<Arguments> provideJumpingPiece() {
        return Stream.of(
                Arguments.of(Knight.from(Color.BLACK)),
                Arguments.of(Pawn.from(Color.BLACK)),
                Arguments.of(King.from(Color.BLACK)),
                Arguments.of(Knight.from(Color.WHITE)),
                Arguments.of(Pawn.from(Color.WHITE)),
                Arguments.of(King.from(Color.WHITE)),
                Arguments.of(Empty.getInstance())
        );
    }
}
