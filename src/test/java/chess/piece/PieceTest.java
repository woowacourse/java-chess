package chess.piece;

import chess.domain.piece.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @ParameterizedTest(name = "각 체스 기물을 생성할 수 있다.")
    @MethodSource("pieceProvider")
    void createPieceSuccessTest(Piece piece) {
        assertThat(piece).isNotNull();
    }

    static Stream<Arguments> pieceProvider() {
        return Stream.of(
                Arguments.arguments(Pawn.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(Knight.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(Bishop.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(Rook.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(Queen.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(King.getInstanceOf(Camp.WHITE)),
                Arguments.arguments(Empty.getInstanceOf(Camp.NONE))
        );
    }
}
