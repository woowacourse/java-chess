package chess;

import chess.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PieceTest {
    // 리펙토링
    @ParameterizedTest(name = "각 체스 기물을 생성할 수 있다.")
    @MethodSource("pieceProvider")
    void createPieceSuccessTest(Piece piece) {
        Assertions.assertThat(piece)
                .isNotNull();
    }

    static Stream<Arguments> pieceProvider() {
        return Stream.of(
                Arguments.arguments(new Pawn(Camp.WHITE)),
                Arguments.arguments(new Knight(Camp.WHITE)),
                Arguments.arguments(new Bishop(Camp.WHITE)),
                Arguments.arguments(new Rook(Camp.WHITE)),
                Arguments.arguments(new Queen(Camp.WHITE)),
                Arguments.arguments(new King(Camp.WHITE)),
                Arguments.arguments(new Empty())
        );
    }
}
