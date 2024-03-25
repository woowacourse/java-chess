package chess.view.output;

import chess.model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceShapeTest {

    @ParameterizedTest
    @MethodSource("providePieceAndExpectedShape")
    @DisplayName("Piece로 출력 모양을 찾는다.")
    void from(Piece piece, PieceShape expectedShape) {
        // when
        PieceShape actualShape = PieceShape.from(piece);

        // then
        assertThat(actualShape).isEqualTo(expectedShape);
    }

    private static Stream<Arguments> providePieceAndExpectedShape() {
        return Stream.of(
                Arguments.of(King.from(Side.BLACK), PieceShape.BLACK_KING),
                Arguments.of(Queen.from(Side.WHITE), PieceShape.WHITE_QUEEN),
                Arguments.of(Bishop.from(Side.BLACK), PieceShape.BLACK_BISHOP)
        );
    }
}
