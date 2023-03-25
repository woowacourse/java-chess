package chess.domain;

import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;

class PieceTypeTest {
    @DisplayName("기물 타입들을 계산한다.")
    @ParameterizedTest
    @MethodSource("sumDummy")
    void sum(final List<PieceType> pieceTypes, final Point expected) {
        // given & when
        final Point sum = PieceType.sum(pieceTypes);

        // expect
        assertThat(sum).isEqualTo(expected);
    }

    static Stream<Arguments> sumDummy() {
        return Stream.of(
                Arguments.arguments(List.of(), Point.create(0)),
                Arguments.arguments(List.of(PAWN), Point.create(1)),
                Arguments.arguments(List.of(ROOK), Point.create(5)),
                Arguments.arguments(List.of(KNIGHT), Point.create(2.5)),
                Arguments.arguments(List.of(BISHOP), Point.create(3)),
                Arguments.arguments(List.of(QUEEN), Point.create(9)),
                Arguments.arguments(List.of(KING), Point.create(0)),
                Arguments.arguments(List.of(EMPTY), Point.create(0)),
                Arguments.arguments(List.of(PAWN, PAWN, PAWN, PAWN), Point.create(4)),
                Arguments.arguments(List.of(PAWN, PAWN, PAWN, BISHOP), Point.create(6)),
                Arguments.arguments(List.of(PAWN, PAWN, KING, BISHOP), Point.create(5)),
                Arguments.arguments(List.of(PAWN, ROOK, KING, BISHOP), Point.create(9)),
                Arguments.arguments(List.of(KNIGHT, ROOK, KING, BISHOP), Point.create(10.5))
        );
    }
}
