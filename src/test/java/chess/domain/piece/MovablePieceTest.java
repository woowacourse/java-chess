package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Role;
import chess.domain.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MovablePieceTest {

    @ParameterizedTest
    @MethodSource("pieceDummy")
    @DisplayName("역할에 따른 기물이 생성된다.")
    void create(Role role, Class<Piece> expectedPieceType) {
        //given
        Side side = Side.from(Color.BLACK);
        //when
        Piece piece = role.create(side);
        //expected
        assertThat(piece).isInstanceOf(expectedPieceType);
    }

    static Stream<Arguments> pieceDummy() {
        return Stream.of(
                Arguments.arguments(Role.PAWN, Pawn.class),
                Arguments.arguments(Role.ROOK, Rook.class),
                Arguments.arguments(Role.KNIGHT, Knight.class),
                Arguments.arguments(Role.BISHOP, Bishop.class),
                Arguments.arguments(Role.QUEEN, Queen.class),
                Arguments.arguments(Role.KING, King.class)
        );
    }

    @Test
    @DisplayName("타겟 기물이 같은 편인지 확인한다.")
    void isSameSide() {
        Side sourceSide = Side.from(Color.WHITE);
        Side sameSide = Side.from(Color.WHITE);
        Side opponenetSide = Side.from(Color.BLACK);
        MovablePiece sourcePiece = new Pawn(sourceSide);
        MovablePiece targetPiece = new King(sameSide);
        MovablePiece opponentPiece = new Queen(opponenetSide);

        assertThat(sourcePiece.isSameSide(targetPiece)).isTrue();
        assertThat(sourcePiece.isSameSide(opponentPiece)).isFalse();
    }
}
