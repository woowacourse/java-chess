package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @ParameterizedTest
    @MethodSource("pieceDummy")
    @DisplayName("역할에 따른 기물이 생성된다.")
    void create(Role role, Class<Piece> expectedPieceType) {
        //given
        Side side = new Side(Color.BLACK);
        //when
        Piece piece = role.create(side);
        //expected
        assertThat(piece).isInstanceOf(expectedPieceType);
    }

    static Stream<Arguments> pieceDummy() {
        return Stream.of(
                Arguments.arguments(Role.EMPTY, Empty.class),
                Arguments.arguments(Role.PAWN, Pawn.class),
                Arguments.arguments(Role.ROOK, Rook.class),
                Arguments.arguments(Role.KNIGHT, Knight.class),
                Arguments.arguments(Role.BISHOP, Bishop.class),
                Arguments.arguments(Role.QUEEN, Queen.class),
                Arguments.arguments(Role.KING, King.class)
        );
    }

}
