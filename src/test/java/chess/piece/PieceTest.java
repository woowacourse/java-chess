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
                Arguments.arguments(new Pawn(Camp.WHITE)),
                Arguments.arguments(new Knight(Camp.WHITE)),
                Arguments.arguments(new Bishop(Camp.WHITE)),
                Arguments.arguments(new Rook(Camp.WHITE)),
                Arguments.arguments(new Queen(Camp.WHITE)),
                Arguments.arguments(new King(Camp.WHITE)),
                Arguments.arguments(new Empty(Camp.NONE))
        );
    }
}
