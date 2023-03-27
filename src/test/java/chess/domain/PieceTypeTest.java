package chess.domain;

import chess.domain.piece.*;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
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

    @DisplayName("기물 타입과 색깔을 통해 기물 구현체를 반환한다.")
    @ParameterizedTest
    @MethodSource("newInstanceDummy")
    void newInstance(final Color color, final PieceType pieceType, final Piece expected) {
        // given & when
        final Piece piece = pieceType.newInstance(color);

        // then
        assertThat(piece).isEqualTo(expected);
    }

    static Stream<Arguments> newInstanceDummy() {
        return Stream.of(
                Arguments.arguments(WHITE, PAWN, Pawn.from(WHITE)),
                Arguments.arguments(WHITE, ROOK, Rook.from(WHITE)),
                Arguments.arguments(WHITE, KNIGHT, Knight.from(WHITE)),
                Arguments.arguments(WHITE, BISHOP, Bishop.from(WHITE)),
                Arguments.arguments(WHITE, QUEEN, Queen.from(WHITE)),
                Arguments.arguments(WHITE, KING, King.from(WHITE)),
                Arguments.arguments(WHITE, EMPTY, Empty.create()),
                Arguments.arguments(BLACK, PAWN, Pawn.from(BLACK)),
                Arguments.arguments(BLACK, ROOK, Rook.from(BLACK)),
                Arguments.arguments(BLACK, KNIGHT, Knight.from(BLACK)),
                Arguments.arguments(BLACK, BISHOP, Bishop.from(BLACK)),
                Arguments.arguments(BLACK, QUEEN, Queen.from(BLACK)),
                Arguments.arguments(BLACK, KING, King.from(BLACK)),
                Arguments.arguments(BLACK, EMPTY, Empty.create())
        );
    }
}
